package com.uplooking.bigdata.until;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.until
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class Md5Utils {
    public static String md5(String str) {
        // 生成一个MD5加密计算摘要
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            // 计算md5值(hash结果值),实际上是8个的字节的长度z
            byte[] md5 = messageDigest.digest(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, md5);//1代表 正数
            return bigInteger.toString(16);//转换为16进制的hex值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
