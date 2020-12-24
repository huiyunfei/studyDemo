**com.example.annotiondemo**

@interface注解demo

description：
主要就是为了搞清楚@interface这个注解的实际应用和一般应用场景

test文件夹是测试注解属性的定义和属性值的获取

--AnnotationTest是测试获取注解值的测试类

--ColorEnum是枚举类

--MetaAnnotation是元注解类（注解的注解）

demo文件夹是一般应用场景

--checkInterface 自定义注解类

--checkTest是测试类

--MyMethod是模拟自己写的方法类

**com.example.innerclassdemo**

常用的实用内部类来生成外部类对象并赋值方法

**threaddemo**

模拟多线程抢票

**com.example.restdemo**

简单的restful接口demo以及各种插件集成

**com.example.invocation**

java代理模式
1.JDK动态代理是实现了被代理对象的接口，HumapProxy.java。cglib是继承了被代理对象。MyInterceptor.java
2.JDK和Cglib都是在运行期生成字节码，JDK是直接写Class字节码，Cglib使用ASM框架写Class字节码，Cglib代理实现更复杂，生成代理类比JDK效率低。
3.JDK调用代理方法，是通过反射机制调用，Cglib是通过FastClass机制直接调用方法，Cglib执行效率更高。

**com.example.rabbitmq**
springboot集成rabbitmq并简单实现三种模式的使用示例
**com.example.rabbitmq2**
项目中实战使用方法


shardingjdbc-dxfl是后端代码使用Sharding-JDBC实现mysql读写分离。mysql配置好db0主数据库，db1 db2两台从数据库，增删改主数据库从数据库会复制主数据库信息
save方法直接走主数据库，select方法走从数据库。MasterSlaveLoadBalanceStrategyType设置轮训的方式读取数据库。

shardingjdbc-fkfb是分库分表