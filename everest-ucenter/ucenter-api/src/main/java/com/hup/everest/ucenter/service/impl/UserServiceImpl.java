package com.hup.everest.ucenter.service.impl;

import com.hup.everest.ucenter.GlobalUser;
import com.hup.everest.ucenter.User;
import com.hup.everest.ucenter.dto.GlobalUserDto;
import com.hup.everest.ucenter.mapper.GlobalUserMapper;
import com.hup.everest.ucenter.mapper.UserMapper;
import com.hup.everest.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author hupeng on 2018/10/19
 * @version 1.0
 * @Description
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private GlobalUserMapper globalUserMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void register(GlobalUserDto globalUserDto) {
        GlobalUser isExits = globalUserMapper.selectByUsername(globalUserDto.getUsername());
        if (isExits != null) {
            return;
        }
        GlobalUser globalUser = new GlobalUser();
        BeanUtils.copyProperties(globalUserDto, globalUser);
        globalUser.setGuid(getUUID());
        globalUserMapper.insertSelective(globalUser);
        User user = new User();
        user.setGuid(globalUser.getGuid());
        user.setMobilePhone(globalUser.getUsername());
        userMapper.insertSelective(user);
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
