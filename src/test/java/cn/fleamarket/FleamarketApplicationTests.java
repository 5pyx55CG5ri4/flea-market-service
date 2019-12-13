package cn.fleamarket;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
class FleamarketApplicationTests {

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return jsonText;
        } finally {
            is.close();
        }
    }

   JavaMailSenderImpl  javaMailSender = new JavaMailSenderImpl();

    @Test
    void contextLoads() throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String param = "{\"number\":\"10\",\"page\":\"1\"}";
        String result = "";
        URL realUrl = new URL("https://fleamarket.fun:8843/product/productList");
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
        // 发送请求参数
        out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        //解析json对象
        System.out.println(result);
    }

//    @Test
//    void email(){
//        javaMailSender.setHost("smtp.163.com");
//        javaMailSender.setUsername("zly_lyp82nlf@163.com");
//        javaMailSender.setPassword("Lyp82nlf");
//        javaMailSender.setPort(25);
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("1559422960@qq.com");
//        simpleMailMessage.setFrom("zly_lyp82nlf@163.com");
//        simpleMailMessage.setSubject("测试");
//        simpleMailMessage.setText("测试");
//        javaMailSender.send(simpleMailMessage);
//        System.out.println("发送成功");
//    }
    @Test
    void getPath(){
        ClassPathResource resource = new ClassPathResource("");
        System.out.println(resource.getClassLoader().getResource(""));
    }
}
