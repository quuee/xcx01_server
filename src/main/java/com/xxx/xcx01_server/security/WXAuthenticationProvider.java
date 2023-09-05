//package com.xxx.xcx01_server.security;
//
//import com.xxx.xcx01_server.entity.UserEntity;
//import com.xxx.xcx01_server.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WXAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        WXAppletAuthenticationToken token = null;
//        if(authentication instanceof WXAppletAuthenticationToken){
//            token = (WXAppletAuthenticationToken)authentication;
//        }
//
//        UserEntity user = new UserEntity();
//        user.setOpenid(token.getOpenid());
//        user.setNickName(token.getNickName());
//        user.setAvatarUrl(token.getAvatarUrl());
//        userService.saveOrUpdateWXUser(user);
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//
//
//}
