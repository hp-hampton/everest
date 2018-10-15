package com.hup.everest.ucenter.controller;

import com.hup.everest.ucenter.mapper.ClientMapper;
import com.hup.everest.ucenter.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hupeng on 2018/10/15
 * @version 1.0
 * @Description
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private ClientMapper clientMapper;

    @GetMapping
    public List<Client> getClient() {
        List<Client> clients = clientMapper.selectAll();
        return clients;
    }
}
