package com.andyron.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author andyron
 **/
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // 临时关掉spring Security
@SpringBootApplication
// 因为mapper的实现类是动态生成的，所以要通过MapperScan注解来指定mapper的路径
@MapperScan(basePackages = "com.andyron.system.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
