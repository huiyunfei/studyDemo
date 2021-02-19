package com.example.mybatisFB;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class ApiVersionUtil {

    // 记录当前线程版本号
    public final static ThreadLocal<String> API_VERSION = new ThreadLocal<String>();

    /**
     * 当前版本是否比目标版本新（版本相同，也算新）
     *
     * @param currentApiVersion
     * @param targetApiVersion
     * @return
     */
    public static boolean isNewVersion(String currentApiVersion, String targetApiVersion) {
        if (StringUtils.isBlank(currentApiVersion) || StringUtils.isBlank(targetApiVersion)) {
            return false;
        }

        try {
            String[] currentArr = currentApiVersion.split("\\.");
            String[] targetArr = targetApiVersion.split("\\.");

            for (int i = 0; i < currentArr.length; i++) {
                if (i < currentArr.length - 1) {   //非最后一个数字
                    if (Integer.parseInt(currentArr[i]) > Integer.parseInt(targetArr[i])) {
                        return true;
                    } else if (Integer.parseInt(currentArr[i]) < Integer.parseInt(targetArr[i])) {
                        return false;
                    }
                } else {    //最后一个数字
                    return Integer.parseInt(currentArr[i]) >= Integer.parseInt(targetArr[i]);
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isNewVersion("3.5.0", "3.5.0"));
        System.out.println(isNewVersion("3.4.5", "3.5.0"));
        System.out.println(isNewVersion("3.5.5", "3.5.0"));
        Assert.isTrue(!isNewVersion(null, "3.5.0"), "不符合预期");
        Assert.isTrue(isNewVersion("3.5.0", "3.5.0"), "不符合预期");
        Assert.isTrue(isNewVersion("3.2.2", "3.2.1"), "不符合预期");
        Assert.isTrue(isNewVersion("10.2.2", "3.2.1"), "不符合预期");
        Assert.isTrue(isNewVersion("10.1.2", "3.2.1"), "不符合预期");
        Assert.isTrue(isNewVersion("10.5.1", "10.4.3"), "不符合预期");
    }
}
