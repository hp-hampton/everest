package com.hup.everest.ucenter.oauth2.config;

import com.hup.everest.ucenter.oauth2.DefaultClientDetailsService;
import com.hup.everest.ucenter.security.MultiAuthenticationProvider;
import com.hup.everest.ucenter.userdetails.DefaultMultiUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author hupeng
 * @version 1.0
 * @Date 2018/8/25 下午6:14
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DefaultClientDetailsService defaultClientDetailsService;

    @Autowired
    private DefaultMultiUserDetailsService defaultMultiUserDetailsService;

    @Autowired
    private MultiAuthenticationProvider multiAuthenticationProvider;

    @Autowired
    private TokenStore tokenStore;

    private AuthenticationManager authenticationManager;


    public AuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(defaultClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(defaultMultiUserDetailsService);

        endpoints.approvalStoreDisabled();
        ProviderManager providerManager = (ProviderManager) authenticationManager;
        providerManager.getProviders().clear();
        providerManager.getProviders().add(multiAuthenticationProvider);
        endpoints.authenticationManager(providerManager)
                .tokenStore(tokenStore);//存储token到Redis

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }


    // @Autowired TokenStore，需要如下配置，否则包找不到TokenStore异常
    @Configuration
    protected static class RelatedConfiguration {
        @Bean
        public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
            RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
            return redisTokenStore;
        }
    }
}
