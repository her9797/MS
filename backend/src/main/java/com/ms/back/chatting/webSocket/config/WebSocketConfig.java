package com.ms.back.chatting.webSocket.config;

import com.ms.back.chatting.webSocket.handler.SocketHandler;
import com.ms.back.chatting.webSocket.interceptor.CustomHandshakeInterceptor;
import com.ms.back.common.Jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final SocketHandler socketHandler;

    private final JwtUtils jwtUtils;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 웹소켓 cors 정책으로 인해, 허용 도메인을 지정
        registry.addHandler(socketHandler, "/wss/messages")
                .addInterceptors(new CustomHandshakeInterceptor(jwtUtils))
                .setAllowedOrigins("http://localhost:3000"); // CORS 허용
    }

}
