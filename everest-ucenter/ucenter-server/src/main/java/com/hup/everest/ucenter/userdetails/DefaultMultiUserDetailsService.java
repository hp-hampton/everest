package com.hup.everest.ucenter.userdetails;

import com.hup.everest.ucenter.GlobalUser;
import com.hup.everest.ucenter.mapper.GlobalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author hupeng
 * @version 1.0
 * @Date 2018/8/24 下午8:16
 */
@Service
public class DefaultMultiUserDetailsService implements MultiUserDetailsService {

    private final String ROLE_USER = "ROLE_USER";

    @Autowired
    private GlobalUserMapper globalUserMapper;


    @Override
    public MultiUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GlobalUser globalUser = globalUserMapper.selectByUsername(username);

        return new MultiUserDetails() {
            @Override
            public String getUserGuid() {
                return globalUser.getGuid();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority(ROLE_USER));
            }

            @Override
            public String getPassword() {
                return globalUser.getPassword();
            }

            @Override
            public String getUsername() {
                return globalUser.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return globalUser.getExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return globalUser.getLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return globalUser.getEnabled();
            }
        };
    }
}
