package com.anxi.webserviceproject.config.auth;

import com.anxi.webserviceproject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 아래 해당 옵션들 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()// URL별 권한 관리를 설정하는 옵션의 시작점 -> antMatchers 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 전체 열람 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER권한을 가진 사람만 "/api/v1/**" 열람 가능
                    .anyRequest().authenticated() // 남은 url에 대해선 로그인 인증한 사람만 열람 가능
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공시 http://localhost:8080/ 으로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() //로그인 사용자 정보를 가져옴
                            .userService(customOAuth2UserService);

    }
}
