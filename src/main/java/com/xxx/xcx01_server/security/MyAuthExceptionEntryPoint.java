package com.xxx.xcx01_server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.xcx01_server.common.exception.ErrorCode;
import com.xxx.xcx01_server.common.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class MyAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper jsonMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {


        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Result<Object> error = Result.error(ErrorCode.UNAUTHORIZED);

        String res = jsonMapper.writeValueAsString(error);
        response.getWriter().write(res);
        response.flushBuffer();

    }
}
