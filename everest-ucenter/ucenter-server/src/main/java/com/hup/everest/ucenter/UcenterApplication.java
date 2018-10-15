package com.hup.everest.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author hupeng on 2018/10/15
 * @version 1.0
 * @Description
 */
@MapperScan("com.hup.everest.ucenter.mapper")
@SpringBootApplication
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
