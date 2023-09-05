package com.xxx.xcx01_server.entity;

public class PayloadDTO {

    // 主题
    private String sub;
    //签发时间
    private Long iat;

    //用户名称
//    private String nickname;

    private String openid;


    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
