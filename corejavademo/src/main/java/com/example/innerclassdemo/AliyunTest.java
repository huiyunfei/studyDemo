package com.example.innerclassdemo;

/**
 * 模拟使用内部类来生成外部类对象和赋值
 * 1:创建外部类构造对象，参数为内部类对象
 * 2：创建内部类的静态生成方法，使用的时候可以直接外部类.方法获得内部类
 * 3：内部类提供属性set方法，返回当前对象
 * 4：内部类提供生成外部类方法，方法内部调用1
 * 实用场景，属性比较多的dto对象，不需要频繁的new对象和一个个的去set属性。
 * Created by huiyunfei on 2019/4/12.
 */
public class AliyunTest {

    private String key;

    private String secret;

    private String bucketName;

    private String endPoint;
    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getEndPoint() {
        return endPoint;
    }
    public AliyunTest(Builder builder) {
        this.bucketName=builder.bucketName;
        this.key=builder.key;
        this.endPoint=builder.endPoint;
        this.secret=builder.secret;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{

        private String key;
        private String secret;
        private String bucketName;
        private String endPoint;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder setBucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public Builder setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public AliyunTest create(){
            return new AliyunTest(this);
        }


    }

    public static void main(String[] args) {
        AliyunTest a=AliyunTest
                .builder()
                .setBucketName("bucketName").setKey("123").setSecret("456")
                .create();
        System.out.println(a.getBucketName());
        System.out.println(a.getKey());
    }
}















