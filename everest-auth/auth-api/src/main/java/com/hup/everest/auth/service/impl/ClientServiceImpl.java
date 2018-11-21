package com.hup.everest.auth.service.impl;

import com.hup.everest.auth.mapper.ClientMapper;
import com.hup.everest.auth.model.Client;
import com.hup.everest.auth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hupeng on 2018/11/20
 * @version 1.0
 * @Description
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void create(Client client) {
        clientMapper.insertSelective(client);
    }

    @Override
    public void modify(Client client) {

    }

    @Override
    public Client selectByClientId(String clientId) {
        return clientMapper.selectByClientId(clientId);
    }
}
