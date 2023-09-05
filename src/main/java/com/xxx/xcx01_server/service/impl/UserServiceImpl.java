package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.xcx01_server.entity.UserEntity;
import com.xxx.xcx01_server.mapper.UserMapper;
import com.xxx.xcx01_server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public boolean saveOrUpdateWXUser(UserEntity user) {
        if(!StringUtils.hasText(user.getOpenid())){
            return false;
        }
        //判断是否存在
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getOpenid, user.getOpenid());
        UserEntity selectOne = baseMapper.selectOne(wrapper);
        if (selectOne == null) {
            user.setRegisterDate(new Date());
            user.setLastLoginDate(new Date());
            baseMapper.insert(user);
        }else {
            selectOne.setNickName(user.getNickName());
            selectOne.setAvatarUrl(user.getAvatarUrl());
            selectOne.setLastLoginDate(new Date());
            baseMapper.updateById(selectOne);
        }
        return true;
    }

    @Override
    public UserEntity getByOpenid(String openid) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getOpenid, openid);
        UserEntity selectOne = baseMapper.selectOne(wrapper);
        return selectOne;
    }
}
