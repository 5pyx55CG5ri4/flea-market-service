package cn.fleamarket;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.fleamarket.mapper")
@EnableSwagger2
public class FleamarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(FleamarketApplication.class, args);
    }

}
