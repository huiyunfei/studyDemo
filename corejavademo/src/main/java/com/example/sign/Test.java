package com.example.sign;

import java.security.MessageDigest;

/**
 * Created by hui.yunfei@qq.com on 2020/7/2
 */
public class Test {

    final public static Integer TTL=1;

    public static void main(String[] args) {
        String str ="{\n" +
                "            \\\"dishType\\\":1,\n" +
                "            \\\"endTime\\\":\\\"\\\",\n" +
                "            \\\"pageNow\\\":1,\n" +
                "            \\\"sellInfoId\\\":573,\n" +
                "            \\\"begainTime\\\":\\\"2019-09-22\\\",\n" +
                "            \\\"pageSize\\\":10\n" +
                "        }appKey=cee736bb992d42e59fd9c87f348b22f2format=jsontimestamp=1590719748668version=3.9.5signatureMethod=md589bc8adea10a4bbc8730782305a9adff";

        String sign = MD5(str);


        System.out.println(sign);   //控制台输出加密

    }

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
