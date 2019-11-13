package cn.fleamarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**Swagger2配置
 * @author zining
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket accessToken2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("所有接口")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("cn.fleamarket.controller")) // 拦截的包路径
                //.paths(PathSelectors.regex("/user/.*"))// 拦截的接口路径
                .paths(PathSelectors.any())  //拦截所有接口
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("FleaMarket接口文档")// 标题
//                .description("")// 描述
                .version("1.0")// 版本
                .build();
    }


}
