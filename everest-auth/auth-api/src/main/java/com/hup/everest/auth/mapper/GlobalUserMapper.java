package com.hup.everest.auth.mapper;

import com.hup.everest.auth.model.GlobalUser;
import tk.mybatis.mapper.common.Mapper;

public interface GlobalUserMapper extends Mapper<GlobalUser> {

    GlobalUser selectByUsername(String loginName);

}