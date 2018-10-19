package com.hup.everest.ucenter.controller;

import com.hup.everest.ucenter.dto.GlobalUserDto;
import com.hup.everest.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng on 2018/10/19
 * @version 1.0
 * @Description
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void register(@RequestBody GlobalUserDto globalUserDto) {
        globalUserDto.setPassword(passwordEncoder.encode(globalUserDto.getPassword()));
        userService.register(globalUserDto);
    }

    @GetMapping
    public String test() {
        return "hello";
    }
}
