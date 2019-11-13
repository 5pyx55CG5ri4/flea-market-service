package cn.fleamarket.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 字符串工具
 */
public class StringTool {
    private StringTool() {

    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String getMd5(String str) {
        String pas = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            pas = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pas;
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Java对象转JSON对象
     * @param object
     * @return
     */
    public static JSONObject ObjectToJSONObject(Object object) {
        return JSONObject.parseObject(JSON.toJSONString(object));
    }
}
