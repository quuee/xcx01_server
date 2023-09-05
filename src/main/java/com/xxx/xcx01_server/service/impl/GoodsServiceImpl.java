package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.xxx.xcx01_server.convert.GoodsConvert;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.entity.GoodsSwiperEntity;
import com.xxx.xcx01_server.mapper.GoodsMapper;
import com.xxx.xcx01_server.service.GoodsService;
import com.xxx.xcx01_server.service.GoodsSwiperService;
import com.xxx.xcx01_server.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements GoodsService {

    @Autowired
    private GoodsConvert goodsConvert;
    @Autowired
    private GoodsSwiperService goodsSwiperService;

    @Autowired
    private Index goodsIndex;

    @Override
    public List<GoodsEntity> goodsListByCategory(Long categoryId) {
        LambdaQueryWrapper<GoodsEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsEntity::getCategoryId,categoryId);
        List<GoodsEntity> goodsEntities = baseMapper.selectList(queryWrapper);
        return goodsEntities;
    }

    @Override
    public Map<String,List<GoodsEntity>> goodsListByCategoryGroupByLabel(Long categoryId) {
        List<GoodsEntity> goodsEntities = baseMapper.selectDetailByCategoryId(categoryId);
        //按标签分组
        Map<String, List<GoodsEntity>> collect = goodsEntities.stream().collect(Collectors.groupingBy(GoodsEntity::getLabel));
        return collect;
    }

    @Override
    public GoodsVO goodsDetailById(Long goodsId) {
        GoodsEntity goodsEntity = baseMapper.selectDetailById(goodsId);
        GoodsVO goodsVO = goodsConvert.convert(goodsEntity);
        List<GoodsSwiperEntity> goodsSwiperEntities = goodsSwiperService.getByGoodsId(goodsId);
        goodsVO.setGoodsSwiperList(goodsSwiperEntities);
        return goodsVO;
    }


    @Override
    public Page<GoodsVO> searchGoods(Integer pageNo, Integer pageSize, String searchWord) {
        Page<GoodsEntity> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<GoodsEntity> like = new LambdaQueryWrapper<GoodsEntity>().like(GoodsEntity::getGoodsName, searchWord);
        page = baseMapper.selectPage(page, like);

        List<GoodsVO> goodsVOS = goodsConvert.convertList(page.getRecords());

        Page<GoodsVO> goodsVOPage = new Page<>(pageNo, pageSize);
        goodsVOPage.setRecords(goodsVOS);
        goodsVOPage.setTotal(page.getTotal());
        goodsVOPage.setSize(page.getSize());

        return goodsVOPage;
    }

    public List<HashMap<String, Object>> searchGoodsByIndex(Integer pageNo, Integer pageSize, String searchWord) throws MeilisearchException {
        SearchRequest searchRequest = SearchRequest.builder().offset(pageNo).limit(pageSize).q(searchWord).build();
        List<HashMap<String, Object>> hits = goodsIndex.search(searchRequest).getHits();
        return hits;
    }
}
