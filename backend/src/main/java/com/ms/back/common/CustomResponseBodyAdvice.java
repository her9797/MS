package com.ms.back.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /** 공통 헤더로 설정하여 각 메서드에 기입할 필요 x */

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 특정 URL 패턴을 체크
        if (request.getURI().getPath().contains("/uploadUserProfile")) {
            // 파일 업로드 요청의 경우 Content-Type을 다르게 설정
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        } else {
            // 그 외의 요청에 대해 Custom Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Custom-Header", "CustomValue");
            response.getHeaders().addAll(headers);
        }

        return body;
    }

}
