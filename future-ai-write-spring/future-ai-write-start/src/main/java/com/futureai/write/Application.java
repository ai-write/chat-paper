package com.futureai.write;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author max
 * @description Springboot启动类
 * @date 2024/3/4 15:36
 */
@SpringBootApplication
@MapperScan("com.futureai.write.infrastructure.persistence.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
