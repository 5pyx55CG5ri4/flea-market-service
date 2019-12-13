package cn.fleamarket.config;

import cn.fleamarket.domain.Image;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zining
 * 静态资源映射
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Interceptor()).addPathPatterns("/user/loginOut", "/product/addProduct", "/image/uploadImg"
//                , "/product/productListByUser", "/product/update", "/product/obtained"
//        ,"/product/delete","/message/addMessage","/message/messageListByUser"
//        ,"/message/delete","/favorites/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/static/img/**").addResourceLocations("file:" + Image.WIN_UPLOAD);
        } else {
            registry.addResourceHandler("/static/img/**").addResourceLocations("file:" + Image.LINUX_UPLOAD);
        }
    }
}
