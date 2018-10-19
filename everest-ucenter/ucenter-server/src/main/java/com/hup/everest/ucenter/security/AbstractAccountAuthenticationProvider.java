package com.hup.everest.ucenter.security;

import com.hup.everest.ucenter.oauth2.ClientDetailsHolder;
import com.hup.everest.ucenter.userdetails.MultiUserDetails;
import com.hup.everest.ucenter.userdetails.MultiUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author hupeng
 * 抽象账户身份验证提供者
 */
public abstract class AbstractAccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    protected MultiUserDetailsService multiUserDetailsService;

    public AbstractAccountAuthenticationProvider(MultiUserDetailsService multiUserDetailsService) {
        this.multiUserDetailsService = multiUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        MultiUserDetails multiUserDetails = null;
        try {
            multiUserDetails = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
        } catch (Exception e) {

        }
        Assert.notNull(multiUserDetails, "retrieveUser returned null - a violation of the interface contract");
        Map<String, String> details = (Map<String, String>) authentication.getDetails();
        ClientDetails clientDetails = ClientDetailsHolder.get();
        details.put("clientId", clientDetails.getClientId());
        details.put("userGuid", String.valueOf(multiUserDetails.getUserGuid()));
        details.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return createAuthentication(authentication, multiUserDetails, details);
    }

    protected Authentication createAuthentication(Authentication authentication, UserDetails userDetails, Map<String, String> details) {
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), authentication.getCredentials(), userDetails.getAuthorities());
        result.setDetails(details);
        return result;
    }

    @Override
    protected MultiUserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return multiUserDetailsService.loadUserByUsername(username);
    }
}
