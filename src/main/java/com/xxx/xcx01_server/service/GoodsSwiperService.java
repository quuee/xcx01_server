package com.xxx.xcx01_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.xcx01_server.entity.GoodsSwiperEntity;

import java.util.List;

public interface GoodsSwiperService extends IService<GoodsSwiperEntity> {

    List<GoodsSwiperEntity> getByGoodsId(Long goodsId);
}
