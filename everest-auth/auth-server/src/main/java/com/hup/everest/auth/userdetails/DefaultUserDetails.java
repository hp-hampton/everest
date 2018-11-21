package com.hup.everest.auth.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface DefaultUserDetails extends UserDetails {

    /**
     * 获取用户 GUID
     *
     * @return
     */
    String getUserGuid();
}
