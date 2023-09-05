package com.xxx.xcx01_server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.xxx.xcx01_server.common.utils.JWTUtil;
import com.xxx.xcx01_server.common.utils.SnowFlake;
import com.xxx.xcx01_server.entity.PayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WXAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper jsonMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        WXAppletAuthenticationToken wxAppletAuthenticationToken =  (WXAppletAuthenticationToken)authentication;

        PayloadDTO payloadDTO = new PayloadDTO();
        payloadDTO.setOpenid(wxAppletAuthenticationToken.getOpenid());
        payloadDTO.setSub("xcx");
        payloadDTO.setIat(new Date().getTime());
//        SnowFlake snowFlake = new SnowFlake(1, 1);


        String token = null;
        try {
            token = JWTUtil.generateToken(payloadDTO);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
//        String refreshToken = JWTUtil.generateToken(wxAppletAuthenticationToken.getOpenid(), wxAppletAuthenticationToken.getId(), wxAppletAuthenticationToken.getNickName(),10);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("msg", "success");
        resMap.put("code", 0);
        resMap.put("token", token);

//        Result<String> ok = Result.ok(token);

        String resJson = jsonMapper.writeValueAsString(resMap);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(resJson);
        response.flushBuffer();
    }
}
