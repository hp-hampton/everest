package com.hup.everest.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hupeng on 2018/11/21
 * @version 1.0
 * @Description
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping
    public String getString(){
        return "hello";
    }
}
