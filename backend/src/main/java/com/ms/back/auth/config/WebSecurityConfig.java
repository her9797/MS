package com.ms.back.auth.config;

import com.ms.back.auth.filter.JwtAuthenticationFilter;
import com.ms.back.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtUtils jwtUtils;

    // JwtUtils를 주입받기 위해 생성자 추가
    public WebSecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);

        return http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll() // 공개 엔드포인트 설정
                        .requestMatchers("/login/**").permitAll() // 소셜 로그인 엔드포인트 허용
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // 로그인 페이지 경로 (선택 사항)
                        .defaultSuccessUrl("/home") // 로그인 성공 시 이동할 기본 URL
                        .failureUrl("/login?error") // 로그인 실패 시 이동할 URL
                        .authorizationEndpoint(authorization ->
                                authorization.baseUri("/oauth2/authorize") // 인증 엔드포인트 기본 URI
                        )
                        .redirectionEndpoint(redirection ->
                                redirection.baseUri("/login/oauth2/code/*") // 리디렉션 URI
                        )

                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
                .build();
    }
}
