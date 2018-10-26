package com.hup.everest.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author hupeng on 2018/10/15
 * @version 1.0
 * @Description
 */
@MapperScan("com.hup.everest.ucenter.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableResourceServer
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
