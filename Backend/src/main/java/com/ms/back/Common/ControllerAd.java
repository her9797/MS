package com.ms.back.Common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;

@RestControllerAdvice(annotations = RestController.class)
public class ControllerAd {

    /** 공통 헤더로 설정하여 각 메서드에 기입할 필요 x */
    @ModelAttribute
    public HttpHeaders commonHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return headers;

    }

}
