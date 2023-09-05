package com.xxx.xcx01_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.xcx01_server.entity.GoodsEntity;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsEntity> {

    GoodsEntity selectDetailById(Long id);
    List<GoodsEntity> selectDetailByCategoryId(Long categoryId);
}
