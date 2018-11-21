package com.hup.everest.auth.service;

import com.hup.everest.auth.model.Client;

/**
 * @author hupeng on 2018/11/20
 * @version 1.0
 * @Description
 */
public interface ClientService {

    void create(Client client);

    void modify(Client client);

    Client selectByClientId(String clientId);
}
