package com.hup.everest.auth.ouath;

import com.hup.everest.auth.mapper.ClientMapper;
import com.hup.everest.auth.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author hupeng on 2018/11/20
 * @version 1.0
 * @Description
 */
@Service
public class DefaultClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Client client = clientMapper.selectByClientId(clientId);
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(client.getClientId());
        clientDetails.setClientSecret(client.getClientSecret());
        clientDetails.setScope(Arrays.asList(StringUtils.tokenizeToStringArray(client.getScope(), ",")));
        clientDetails.setAutoApproveScopes(Arrays.asList(StringUtils.tokenizeToStringArray(client.getAutoapprove(), ",")));
        clientDetails.setResourceIds(Arrays.asList(StringUtils.tokenizeToStringArray(client.getResourceIds(), ",")));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(StringUtils.tokenizeToStringArray(client.getAuthorizedGrantTypes(), ", ")));
        clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        if (StringUtils.hasText(client.getAuthorities())) {
            clientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()));
        }
        //ClientDetailsHolder.set(clientDetails);
        return clientDetails;
    }
}
