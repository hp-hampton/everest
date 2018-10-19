package com.hup.everest.ucenter.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author hupeng
 */
public interface MultiUserDetailsService extends UserDetailsService {

    MultiUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
