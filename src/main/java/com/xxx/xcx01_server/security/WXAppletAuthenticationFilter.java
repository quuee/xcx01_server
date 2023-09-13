package com.xxx.xcx01_server.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.xcx01_server.common.utils.WXUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


public class WXAppletAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper jsonMapper;

    private WXUtil wxUtil;

    private static final String defaultFilterProcessesUrl="/user/login";

    public WXAppletAuthenticationFilter() {
        super(defaultFilterProcessesUrl);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!"POST".equalsIgnoreCase(request.getMethod())){
            throw new HttpRequestMethodNotSupportedException(request.getMethod());
        }

        if(!"application/json".equalsIgnoreCase(request.getContentType())){
            throw new HttpMediaTypeNotSupportedException(request.getContentType());
        }

        StringBuilder stringBuffer = new StringBuilder();
        String line = null;
        BufferedReader reader = request.getReader();
        while((line = reader.readLine())!=null){
            stringBuffer.append(line);
        }
        String jsonString = stringBuffer.toString().replaceAll("\\s", "").replaceAll("\n", "");
//        System.out.println(jsonString);
        JsonNode jsonNode = jsonMapper.readTree(jsonString);

        //取js_code
        JsonNode jsCode = jsonNode.get("jsCode");
        JsonNode nickname = jsonNode.get("nickName");
        JsonNode avatarUrl = jsonNode.get("avatarUrl");
        if(ObjectUtils.isEmpty(jsCode)){
            throw new MissingServletRequestParameterException("jsCode","String");
        }
        //微信code2session，获取openid session_key
        Map<String, Object> code2Session = wxUtil.code2Session(jsCode.asText());
        String sessionKey = String.valueOf(code2Session.get("session_key")==null?"":code2Session.get("session_key"));
        String openid = String.valueOf(code2Session.get("openid")==null?"":code2Session.get("openid"));
        if(ObjectUtils.isEmpty(sessionKey) || ObjectUtils.isEmpty(openid)){
            throw new RuntimeException("无法获取openid session_key");
        }

        WXAppletAuthenticationToken wxAppletAuthenticationToken = new WXAppletAuthenticationToken(openid);
        return this.getAuthenticationManager().authenticate(wxAppletAuthenticationToken);
    }



    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void setWxUtil(WXUtil wxUtil) {
        this.wxUtil = wxUtil;
    }
}
