package com.hup.everest.auth.userdetails;

import com.hup.everest.auth.mapper.GlobalUserMapper;
import com.hup.everest.auth.model.GlobalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author hupeng on 2018/11/19
 * @version 1.0
 * @Description
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private GlobalUserMapper globalUserMapper;

    private final String ROLE_USER = "ROLE_USER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GlobalUser globalUser = globalUserMapper.selectByUsername(username);
        if (globalUser == null)
            throw new UsernameNotFoundException("");
        return new DefaultUserDetails() {
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
                return globalUser.getExpired();
            }

            @Override
            public boolean isEnabled() {
                return globalUser.getEnabled();
            }
        };
    }
}
