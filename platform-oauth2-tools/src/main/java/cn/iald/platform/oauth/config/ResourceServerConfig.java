package cn.iald.platform.oauth.config;

import cn.hutool.core.util.StrUtil;
import cn.iald.platform.oauth.component.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

/**
 * 地址安全认证配置
 *
 * @author wangyc
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Value("${resourceId:}")
    private String resourceId;

    @Value("${signingKey:}")
    private String signingKey;

    /**
     * token白名单
     */
    @Value("${oauth.white-list-url:}")
    private String whiteListUrl;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId).tokenStore(tokenStore()).authenticationEntryPoint(authExceptionEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] whiteListUrlArr;
        if (StrUtil.isNotBlank(whiteListUrl)) {
            whiteListUrl = whiteListUrl.replace(" ", "");
            whiteListUrlArr = whiteListUrl.split(",");
        } else {
            whiteListUrlArr = new String[]{"**"};
        }
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(whiteListUrlArr).permitAll()
                .anyRequest().authenticated()
                .and()
                //统一自定义异常
                .exceptionHandling()
                .and()
                .csrf().disable();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter jwtTokenEnhancer = new JwtAccessTokenConverter();
        jwtTokenEnhancer.setSigningKey(signingKey);
        return jwtTokenEnhancer;
    }
}
