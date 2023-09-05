package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.xcx01_server.entity.GoodsSwiperEntity;
import com.xxx.xcx01_server.mapper.GoodsSwiperMapper;
import com.xxx.xcx01_server.service.GoodsSwiperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSwiperServiceImpl extends ServiceImpl<GoodsSwiperMapper, GoodsSwiperEntity> implements GoodsSwiperService {


    @Override
    public List<GoodsSwiperEntity> getByGoodsId(Long goodsId) {
        LambdaQueryWrapper<GoodsSwiperEntity> goodsSwiperEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsSwiperEntityLambdaQueryWrapper.eq(GoodsSwiperEntity::getGoodsId,goodsId).orderByAsc(GoodsSwiperEntity::getSort);
        return baseMapper.selectList(goodsSwiperEntityLambdaQueryWrapper);
    }
}
