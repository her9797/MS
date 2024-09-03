package com.ms.back.chatting.webSocket.interceptor;

import com.ms.back.common.Jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtUtils jwtUtils;

    public CustomHandshakeInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        String token = servletRequest.getServletRequest().getParameter("token");

        log.info("WebSocket handshake TOKEN 검증 : " + token);

        if (token != null && jwtUtils.validateToken(token)) {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtUtils.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            attributes.put("user", claims.getSubject());
            log.info("Attributes : {}", attributes);
            return true;
        }

        log.warn("TOKEN 존재하지 않음");
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        if (ex != null) {
            // 핸드셰이크 중 발생한 예외를 처리 예정
            log.error("WebSocket handshake 실패", ex);
        } else {
            // 핸드셰이크가 성공적으로 완료된 후의 작업을 수행 예정
            log.info("WebSocket handshake 성공!");
        }
    }
}
