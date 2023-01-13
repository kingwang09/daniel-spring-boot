package net.freehongs.daniel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@EnableResourceServer
@Configuration
public class SecurityResourceServerConfig implements ResourceServerConfigurer {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("account");
    }

    /**
     * TODO
     *  - GET /api/accounts
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous()
                .and()
            .authorizeRequests()
                //회원가입만 모두가 가능
                .mvcMatchers(HttpMethod.POST, "/api/accounts")
                    .anonymous()
                //그 외의 회원 정보는 인증된 경우만 가능
                .mvcMatchers("/api/accounts/**")
                    .authenticated()
                //회원정보 어드민
                .mvcMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
