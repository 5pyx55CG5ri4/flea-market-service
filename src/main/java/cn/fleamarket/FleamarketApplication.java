package cn.fleamarket;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.fleamarket.mapper")
public class FleamarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleamarketApplication.class, args);
    }

}
