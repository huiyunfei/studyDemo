package com.example.function;

/**
 * 自定义函数式接口 无参无返回值
 * Created by hui.yunfei@qq.com on 2019/8/20
 */
@FunctionalInterface
public interface Consumer {

    public void show();

    class Zi implements Consumer {

        @Override
        public void show() {
            System.out.println("子类show");
        }
    }

    class Demo {
        private static void fun(Consumer consumer) {
            consumer.show();
        }

        public static void main(String[] args) {
            //1.new 子类对象
            fun(new Zi());
            //2.方式二：匿名内部类
            fun(new Consumer() {
                @Override
                public void show() {
                    System.out.println("匿名内部子类--show()....");
                }
            });
            //3.方式三：Lambda表达式--
            //使用"无名的方法"代替了子类的show()
            fun(() -> {
                System.out.println("Lambda表达式--show()");
            });

            //4.调用jdk提供的消费型接口
            f1(new java.util.function.Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println("s:" + s);
                }
            }, "123");

            //5.lambda表达式调用消费型接口
            f1(s ->System.out.println("s2:" + s), "456");

        }

        private static void f1(java.util.function.Consumer<String> s1, String s2) {
            s1.accept(s2);
        }
    }
}


