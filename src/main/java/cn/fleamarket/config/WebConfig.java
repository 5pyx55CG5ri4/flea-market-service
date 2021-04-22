package cn.fleamarket.config;

import cn.fleamarket.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
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

 @Autowired
 private PathConfig pathConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/static/img/**").addResourceLocations("file:" + pathConfig.getWinPath());
        } else {
            registry.addResourceHandler("/static/img/**").addResourceLocations("file:" + pathConfig.getLinuxPath());
        }
    }
}
