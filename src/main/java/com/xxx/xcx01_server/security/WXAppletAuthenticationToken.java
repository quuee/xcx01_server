package com.xxx.xcx01_server.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class WXAppletAuthenticationToken extends AbstractAuthenticationToken {

    private String openid;

    private String sessionKey;

    private String nickName;

    private String avatarUrl;

    private Long id;

    /**
     * 生成未认证的AuthenticationToken
     * @param openid
     */
    public WXAppletAuthenticationToken(String openid) {
        super(null);
        this.openid = openid;
        super.setAuthenticated(false);
    }

    /**
     * 生成已认证的AuthenticationToken
     * @param openid
     * @param sessionKey
     * @param id
     * @param nickName
     * @param avatarUrl
     */
    public WXAppletAuthenticationToken(String openid, Long id, String nickName, String avatarUrl) {
        super(null);
        this.openid = openid;
        this.id = id;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        super.setAuthenticated(true);
    }

    public WXAppletAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.openid;
    }

    @Override
    public Object getPrincipal() {
        return this.sessionKey;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
