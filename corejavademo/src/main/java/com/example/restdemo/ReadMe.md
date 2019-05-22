#这里记录restfuldemo搭建过程

1：Transational事务配置记录
1-1：主启动文件添加@EnableTransactionManagement注解开启事务
1-2：对应service的方法上添加@Transational注解（一般推荐在service.方法上）
事务不起作用记得检查MySQL的引擎是否是innnoDB，方法是否public，异常是否是运行时异常or error。

2：多环境配置
添加application-xxx.yml配置文件，在主application.yml添加spring.profiles.active(这里可以${}动态读取开发工具的变量)
例如idea可以直接在启动配置里添加--DEPLOY=dev变量

3：集成mybatis
3-1：pom文件添加mybatis-spring-boot-starter依赖jar
3-2：启动类添加@MapperScan("com.example.restdemo.mapper")//此处添加注解后dao接口无需再添加@mapper（该注解不需要xml文件可以直接方法上写sql） @repository注解
3-3：#mybatis配置。通常，若mybatis配置信息较少，只是针对基本配置无需复杂配置，则只需在application.yml文件中配置即可，否则最好配置在 mybatis-config.xml中
    mybatis:
      typeAliasesPackage: com.example.restdemo.entity
      mapperLocations: classpath:mapper/*.xml
      #configLocation: classpath:/mybatis-config.xml
3-4：配置文件添加数据源配置
    spring:
      #数据源
      datasource:
        url: jdbc:mysql://localhost:3306/test??useUnicode=true&amp;characterEncoding=UTF-8
        username: root
        password:
        driver-class-name: com.mysql.jdbc.Driver
 
 4：添加logback.xml日志记录
 添加依赖jar包
 <!-- lombok依赖-->
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
</dependency>
<!--Slf4j 依赖-->
<dependency>
   <groupId>org.slf4j</groupId>
   <artifactId>jcl-over-slf4j</artifactId>
</dependency>
<!-- logback 依赖 是slf4j的实现-->
<dependency>
   <groupId>ch.qos.logback</groupId>
   <artifactId>logback-classic</artifactId>
</dependency>
使用类添加@Slf4j注解，idea添加lombok插件可以直接log.使用
 配置文件添加 #控制台打印sql
     logging:
         level:
           com:
             example:
               restdemo:
                 mapper: DEBUG

5:配置德鲁伊数据源
5-1：添加依赖
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.0.18</version>
</dependency>
5-2：配置文件添加参数
#数据源
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8
    username: root
    password:
    driverClassName: com.mysql.jdbc.Driver
  ###################以下为druid增加的配置###########################
    type: com.alibaba.druid.pool.DruidDataSource
  # 下面为连接池的补充设置，应用到上面所有数据源中
  # 初始化大小，最小，最大
    initialSize: 5
    minIdle: 5
    maxActive: 20
  # 配置获取连接等待超时的时间
    maxWait: 60000
  # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
  # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
  # 打开PSCache，并且指定每个连接上PSCache的大小
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
  # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat,wall
  # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
  # 合并多个DruidDataSource的监控数据
  #spring.datasource.useGlobalDataSourceStat: true
  ###############以上为配置druid添加的配置########################################
5-3:添加DruidDBConfig配置文件和DruidConfig配置文件

6：配置swagger
6-1:配置pom文件jar
<!-- swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>
        <!-- swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>
6-2:
controller类、方法添加对应注解
备注：遇到一个问题到现在没解决，就是自定义对象接收参数，按照其他教程都是在对象类上边添加@ApiModel注解，然后参数使用@ApiModelProperty注解，
很奇怪，我只要一加ApiModel注解swagger上就一个参数也看不到，去掉就正常。匪夷所思。。。
json对象之前我们都手动转为封装类就是为了swagger，没找到方便操作的自定义jsonobject和map接收参数的注解。后续有的话再补充
7：配置自定义过滤器
依赖于servlet容器,基于函数回调，主要用于对请求做预处理，判断是否登陆，请求URL权限，过滤字符等，缺点是一个过滤器实例只能在容器初始化时调用一次。
7-1：直接在类上使用@Component @WebFilter注解使用，执行顺序按文件名的首字母来
7-2：添加在configuration中使用bean注解（支持使用第三方过滤器）MyFilterConfig

8:配置自定义监听器
依赖于servlet容器，Listeeshi是servlet规范中定义的一种特殊类。
用于监听servletContext、HttpSession和servletRequest等域对象的创建和销毁事件。
监听域对象的属性发生修改的事件。用于在事件发生前、发生后做一些必要的处理。一般是获取在线人数等业务需求。
8-1：直接在类上使用@Component @WebListener注解使用。
8-2：添加在configuration中使用bean注解 

9：配置自定义拦截器
依赖于SpringMVC框架，基于java反射，缺点只能对controller请求进行拦截，属于面向切面编程（AOP）的一种方法级别的运用，在调用方法前、方法后做操作
9-1：新建拦截器类implements HandlerInterceptor，并重写三个方法
9-2：提供拦截器配置类extends WebMvcConfigurerAdapter

执行顺序： 
request-->filterPre(进入过滤器，执行chain.doFilter之前)-->service(springmvc的doService方法)-->dispatcher（springmvc请求分发）
-->preHandle（进入拦截器，执行controller之前）-->controller-->postHandle（执行完controller，return view之前）
-->afterCompletion（return view之后，filter返回客户端之前）-->filterAfter（服务端完全执行完，返回给客户端之前）

10：添加mybatis一对多关系
10-1：可以不修改原始pojo，添加一那一方的关联pojo,继承原始pojo，添加关联关系List<TestUser> testUsers;
10-2：mapper文件添加一那一方的collection标签，column是多那一方表中保存一这一方的列名，javaType是ArrayList，ofType是多方的pojo，select是find多方By一方列名

11:添加redis
pom添加 <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-data-redis</artifactId>
             </dependency>
     
             <dependency>
                 <groupId>redis.clients</groupId>
                 <artifactId>jedis</artifactId>
                 <version>2.9.0</version>
             </dependency>
             
配置文件：
 redis:
    host: 127.0.0.1
    password:
    port: 6379
    maxWaitMillis: 10000
    maxTotal: 200
    testOnBorrow: true
    testOnReturn: true
    timeout: 3600
    maxIdle: 200
配置类：
RedisConfguration