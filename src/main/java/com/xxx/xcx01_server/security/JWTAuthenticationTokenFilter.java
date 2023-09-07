package com.xxx.xcx01_server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import com.xxx.xcx01_server.common.exception.ErrorCode;
import com.xxx.xcx01_server.common.exception.ServerException;
import com.xxx.xcx01_server.common.utils.JWTUtil;
import com.xxx.xcx01_server.entity.UserEntity;
import com.xxx.xcx01_server.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.text.ParseException;

public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {


    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationTokenFilter.class);

    private UserService userService;

    private ObjectMapper jsonMapper;

    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getRequestURI());

        String token = request.getHeader("Authorization");

        if(!ObjectUtils.isEmpty(token)){
            LinkedTreeMap<String,Object> payload = null;
            try{
                // 全局异常只能拦截到controller层，解决方法：handlerExceptionResolver
                // handlerExceptionResolver.resolveException(request,response,null,new ServerException(ErrorCode.TOKENEXPIRED));
                payload = JWTUtil.verify(token); // 如果token过期，JWT解析时会抛出异常TokenExpiredException
            }
            catch (ParseException e) {
                handlerExceptionResolver.resolveException(request,response,null,new ServerException(ErrorCode.UNAUTHORIZED));
                return;

            } catch (JOSEException e) {
                handlerExceptionResolver.resolveException(request,response,null,new ServerException(ErrorCode.UNAUTHORIZED));
                return;
//                throw new RuntimeException(e);
            }catch (ServerException e){
                if(e.getCode() == ErrorCode.TOKENEXPIRED.getCode()){
                    handlerExceptionResolver.resolveException(request,response,null,new ServerException(ErrorCode.TOKENEXPIRED));
                    return;
                }
            }

            if(!ObjectUtils.isEmpty(payload)){
                String openid = (String) payload.get("openid");
//                PayloadDTO payloadDTO = jsonMapper.readValue(string, PayloadDTO.class);
//                String openid = payloadDTO.getOpenid();
                UserEntity user = userService.getByOpenid(openid);
                WXAppletAuthenticationToken wxAppletAuthenticationToken = new WXAppletAuthenticationToken(user.getOpenid(), user.getId(), user.getNickName(), user.getAvatarUrl());
                SecurityContextHolder.getContext().setAuthentication(wxAppletAuthenticationToken);
            }

        }

        filterChain.doFilter(request,response);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setHandlerExceptionResolver(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }
}
