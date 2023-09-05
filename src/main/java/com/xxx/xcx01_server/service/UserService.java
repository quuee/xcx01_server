package com.xxx.xcx01_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.xcx01_server.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    /**
     * 微信 用户登录时 新增或保存
     * @param user
     * @return
     */
    boolean saveOrUpdateWXUser(UserEntity user);

    UserEntity getByOpenid(String openid);
}
