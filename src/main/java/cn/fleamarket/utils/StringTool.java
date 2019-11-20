package cn.fleamarket.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author zining
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
     *
     * @param object
     * @return
     */
    public static JSONObject ObjectToJSONObject(Object object) {
        return JSONObject.parseObject(JSON.toJSONString(object));
    }

    /**
     * list集合转json数组
     */
    public static JSONArray ListToJsonArray(Object object){
        return JSON.parseArray(JSON.toJSONString(object));
    }

    /**
     * Java日期转字符串
     *
     * @param date
     * @return
     */
    public static String dataTool(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }

    /**
     * 生成验证码
     * @return
     */
    public static String getCodeToString(){
        Integer x = (int) ((Math.random() * 9 + 1) * 10000);
        return x.toString();
    }
}
