package com.ms.back.auth.config;

import com.ms.back.auth.filter.JwtAuthenticationFilter;
import com.ms.back.common.Jwt.JwtUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


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
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // React 앱의 URL
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                    return configuration;
                }))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**", "/notices", "/wss/**").permitAll() // 공개 엔드포인트 설정 , WebSocket jwt 검증 로직 있음
                        .requestMatchers("/auth/**").permitAll() // OAuth2 엔드포인트 허용
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:3000") // 로그인 성공 시 이동할 URL (API 엔드포인트)
                        .failureUrl("http://localhost:3000/login") // 로그인 실패 시 이동할 URL (API 엔드포인트)
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

    /** cors 필터 추가 */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }


}
