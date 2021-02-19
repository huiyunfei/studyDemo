package com.example.mybatisFB;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;

/**
 * 分表Interceptor
 *
 * @author wuyangfan
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
})
public class MybatisSqlInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisSqlInterceptor.class);

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
            //优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
            MetaObject metaObject = MetaObject
                    .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                            new DefaultReflectorFactory());
            //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
            String sqlId = mappedStatement.getId();
            //sql语句类型 select、delete、insert、update
            String sqlCommandType = mappedStatement.getSqlCommandType().toString();

            String className = sqlId.substring(0, sqlId.lastIndexOf("."));

            String methodName = sqlId.replace(className + ".", "");
            Class<?> classObj = Class.forName(className);
            Method[] methods = classObj.getDeclaredMethods();
            Method method = null;
            for (Method method1 : methods) {
                if (method1.getName().equals(methodName)) {
                    method = method1;
                    break;
                }
            }
            TableSeg tableSeg = method.getAnnotation(TableSeg.class);
            if (null == tableSeg) {
                //不需要分表，直接传递给下一个拦截器处理
                return invocation.proceed();
            }
            LOGGER.info("MybatisSqlInterceptor--sqlId:{}", sqlId);
            //获取分表字段
            String[] shardField = tableSeg.shardBy().split(",");
            List<String> timeList = new ArrayList<>();
            String orderId = "";
            List<String> orderIdList = new ArrayList<>();
            Set<String> tableNameSet = new HashSet<String>();
            Object parameterObjects = metaObject.getValue("delegate.boundSql.parameterObject");
            Map<String, Object> parameters = new HashMap<String, Object>();

            if (Map.class.isAssignableFrom(parameterObjects.getClass())) {
                parameters = (Map<String, Object>) parameterObjects;
            } else {
                parameters = OrderTableShardingUtils.Obj2Map(parameterObjects);
            }

            String apiVersion = ApiVersionUtil.API_VERSION.get();
            LOGGER.info("当前请求的apiVersion：{}", apiVersion);
            if (StringUtils.isNotBlank(apiVersion) && !ApiVersionUtil.isNewVersion(apiVersion, "3.9.5")) {
                LOGGER.info("老版本apiVersion：{}不走分表拦截器", apiVersion);
                return invocation.proceed();
            }
            LOGGER.info("MybatisSqlInterceptor--parameters:{}", parameters);
            //获取参数
            for (int i = 0; i < shardField.length; i++) {
                Object paramValue = parameters.get(shardField[i]);
                if (paramValue != null) {
                    System.out.println(paramValue.getClass());
                    if (paramValue.getClass() == String.class) {
                        //参数是订单号或者时间
                        String paramStr = (String) paramValue;
                        String paramIndex = paramStr;
                        if (paramStr.length() > 2) {
                        	paramIndex = paramStr.substring(0, 2);
                        }
                        if (paramIndex.equals("DD")) {
                            //订单号
                            orderId = paramStr;
                            String tableName = OrderTableShardingUtils.getTableNameByOrderId(orderId);
                            if (tableName != null) {
                                tableNameSet.add(tableName);
                            }

                        } else {
                            //时间
                            //boolean isDate = DateUtils.checkDate(paramStr, "yyyy-MM-dd HH:mm:ss");
                            boolean isDate = true;

                            if (isDate) {
                                //时间
                                timeList.add(paramStr);
                            }
                        }
                    } else if (List.class.isAssignableFrom(paramValue.getClass())) {
                        //参数是orderList
                        orderIdList = (List<String>) paramValue;
                        if (orderIdList != null && orderIdList.size() > 0) {
                            for (String ordersId : orderIdList) {
                                String tableName = OrderTableShardingUtils.getTableNameByOrderId(ordersId);
                                if (tableName != null) {
                                    tableNameSet.add(tableName);
                                }
                            }
                        }
                    }
                }
            }
            //判断时间
            Date beginTime = null;
            Date endTime = null;
            if (timeList.size() == 2) {
                //beginTime = DateUtils.strToDateLong(timeList.get(0));
                //endTime = DateUtils.strToDateLong(timeList.get(1));
                List<String> tableName = new ArrayList<>();
                if (beginTime.getTime() > endTime.getTime()) {
                    tableName = OrderTableShardingUtils.getTableNameListByTimes(timeList.get(1), timeList.get(0));
                } else {
                    tableName = OrderTableShardingUtils.getTableNameListByTimes(timeList.get(0), timeList.get(1));
                }
                if (tableName != null && tableName.size() > 0) {
                    for (String tn : tableName) {
                        if (tn != null) {
                            tableNameSet.add(tn);
                        }
                    }
                }

            }

            //拦截查询sql
            if (sqlCommandType.equals("SELECT")) {
                BoundSql boundSql = statementHandler.getBoundSql();
                String sql = boundSql.getSql();
                if (sql.toLowerCase().indexOf("tb_shop_order") < 0 || sql.toLowerCase().indexOf("tb_shop_order_") >= 0) {
                    return invocation.proceed();
                }
                if (tableNameSet.size() == 0) {
                    return invocation.proceed();
                }
                Iterator<String> it = tableNameSet.iterator();
                List<String> tableNameList = new ArrayList<>();
                while (it.hasNext()) {
                    tableNameList.add(it.next());
                }
                String pattern = "(?i)tb_shop_order";
                if (tableNameList.size() == 1) {
                    sql = sql.replaceAll(pattern, tableNameList.get(0));
                } else {
                    for (int i = 0; i < tableNameList.size(); i++) {
                        if (i == 0) {
                            sql = sql.replaceAll(pattern, tableNameList.get(0));
                        }
                    }
                }
                LOGGER.info("MybatisSqlInterceptor--sql:{}", sql);
                metaObject.setValue("delegate.boundSql.sql", sql);

            } else {
                return invocation.proceed();
            }
        } catch (Exception e) {
            LOGGER.error("MybatisSqlInterceptor--Exception:{}", e);
        }

        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);

    }

    @Override
    public void setProperties(Properties properties) {

    }

    //  定义一个内部辅助类，作用是包装sql
    class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
