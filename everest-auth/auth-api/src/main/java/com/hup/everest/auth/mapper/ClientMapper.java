package com.hup.everest.auth.mapper;

import com.hup.everest.auth.model.Client;
import tk.mybatis.mapper.common.Mapper;

public interface ClientMapper extends Mapper<Client> {

    Client selectByClientId(String clientId);
}