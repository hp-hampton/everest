package com.hup.everest.ucenter.mapper;


import com.hup.everest.ucenter.Client;
import tk.mybatis.mapper.common.Mapper;

public interface ClientMapper extends Mapper<Client> {

    Client selectByClientId(String clientId);
}