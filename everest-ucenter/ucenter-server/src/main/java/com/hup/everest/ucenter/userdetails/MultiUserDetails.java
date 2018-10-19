package com.hup.everest.ucenter.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface MultiUserDetails extends UserDetails {

    /**
     * 获取用户 GUID
     *
     * @return
     */
    String getUserGuid();
}
