package com.hup.everest.ucenter.mapper;

import com.hup.everest.ucenter.GlobalUser;
import tk.mybatis.mapper.common.Mapper;

public interface GlobalUserMapper extends Mapper<GlobalUser> {

    GlobalUser selectByUsername(String loginName);

}