package com.xxx.xcx01_server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.xcx01_server.common.utils.JWTUtil;
import com.xxx.xcx01_server.common.utils.Result;
import com.xxx.xcx01_server.config.WXProperties;
import com.xxx.xcx01_server.entity.UserEntity;
import com.xxx.xcx01_server.security.WXAppletAuthenticationToken;
import com.xxx.xcx01_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("user")
@RestController
public class WxUserController {

    @Autowired
    private WXProperties wxProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    public Result<WXAppletAuthenticationToken> userInfo(){
        WXAppletAuthenticationToken authentication = (WXAppletAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        return Result.ok(authentication);
    }


}
