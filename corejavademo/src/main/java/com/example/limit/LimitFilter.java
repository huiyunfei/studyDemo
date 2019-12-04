package com.example.limit;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bjj.access.modal.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hui.yunfei@qq.com on 2019/12/4
 */

@WebFilter(filterName = "ajaxCorsFilter",urlPatterns = "/*")
public class LimitFilter  implements Filter {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 骑手限流器开关,1:开启;其他关闭.
     */
    @Value("${rider.limiter}")
    private String limiter;

    /**
     * 限流时间，秒
     */
    @Value("${rider.limiterTime}")
    private Integer limiterTime ;

    /**
     * 限流次数
     */
    @Value("${rider.limiterCount}")
    private Integer limiterCount ;

    /**
     * 不过滤的url
     */
    private final static List<String> noFilterUrl = Arrays.asList("/assignRiderOrderTake","/getSellRider",
            "/deleteRiderAccountIdByRiderId", "/toUploadRiderSellInfo",
            "/getRiderInformation", "/findRiderInfoByIds", "/getTbRiderAccount", "/python/orders");

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("############################################filter初始化############################################");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("############################################filterDoFilter############################################");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri=request.getRequestURI();
        boolean flag=true;
        //内部服务调用接口以及平台推单接口不做限流处理
        for (String s : noFilterUrl){
            if (uri.endsWith(s)){
                flag = false;
            }
        }

        if (flag && "1".equals(limiter)) {
            //开始限流校验
            String params = request.getParameter("params");
            String token=null;
            if(params!=null){token=(String) JSONObject.parseObject(params).get("token");}
//            log.info("############################################IPADDRESSES:{}.TOKEN:{}",ipAddresses,token);
            //只限流登陆后的操作
            if(token!=null){
                String key ="aaa"+uri+token;
                String s = redisTemplate.opsForValue().get(key);

                if(s==null){
                    //初始化
//                    log.info("############################################INIT");

                    redisTemplate.opsForValue().set( key, "1", limiterTime, TimeUnit.SECONDS);
                }else if(Integer.valueOf(s)>=limiterCount){
                    //增加黑名单
                    String ipAddresses = request.getHeader("X-Forwarded-For");
                    String riderId = redisTemplate.opsForValue().get("bbb" + token);
                    redisTemplate.delete("aaa"+token);

                    response.setStatus(HttpServletResponse.SC_OK);
                    ResultObj result = new ResultObj();
                    result.setInfo(HttpServletResponse.SC_UNAUTHORIZED);
                    result.setMsg("访问过于频繁，请稍后再试");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue));
                    return;
                }else{
                    //增长
//                    log.info("############################################INCREMENT:{}",increment);
                    Long increment = redisTemplate.opsForValue().increment(key, 1);
                    if(increment==1){
                        redisTemplate.expire(key,limiterTime, TimeUnit.SECONDS);
                    }
                }
            }



            filterChain.doFilter(request, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    /*private void printLog(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        if(headerNames.hasMoreElements()){
            for (String i = headerNames.nextElement(); i !=null; i=headerNames.nextElement()) {
                log.info("HEADER.key:{}.Value:{}",i,request.getHeader(i));
            }
        }
    }*/

}
