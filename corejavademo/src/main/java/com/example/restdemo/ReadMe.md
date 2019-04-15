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
