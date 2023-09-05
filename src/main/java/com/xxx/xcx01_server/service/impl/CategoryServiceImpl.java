package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.xcx01_server.convert.CategoryConvert;
import com.xxx.xcx01_server.entity.CategoryEntity;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.mapper.CategoryMapper;
import com.xxx.xcx01_server.service.CategoryService;
import com.xxx.xcx01_server.service.GoodsService;
import com.xxx.xcx01_server.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryConvert categoryConvert;

    @Override
    public List<CategoryVO> categoryPageList() {
        LambdaQueryWrapper<CategoryEntity> categoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryEntityLambdaQueryWrapper.orderByAsc(CategoryEntity::getSort);
        categoryEntityLambdaQueryWrapper.eq(CategoryEntity::getHigherId,0);
        List<CategoryEntity> categoryEntities = baseMapper.selectList(categoryEntityLambdaQueryWrapper);

        List<CategoryVO> voList = categoryConvert.convertList(categoryEntities);

        return voList;
    }
}
