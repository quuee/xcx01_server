package com.xxx.xcx01_server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.vo.GoodsVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<GoodsEntity> {

    List<GoodsEntity> goodsListByCategory(Long categoryId);

    /**
     * 通过分类查询商品 并按标签分组
     * @param categoryId
     * @return
     */
    Map<String,List<GoodsEntity>> goodsListByCategoryGroupByLabel(Long categoryId);

    /**
     * 查询商品详情
     * @param goodsId
     * @return
     */
    GoodsVO goodsDetailById(Long goodsId);

    /**
     * 简单实现，最好有搜索引擎支持
     * @param pageNo
     * @param pageSize
     * @param searchWord
     * @return
     */
    Page<GoodsVO> searchGoods(Integer pageNo, Integer pageSize, String searchWord);

    /**
     * 搜索引擎查询
     * @param pageNo
     * @param pageSize
     * @param searchWord
     * @return
     * @throws MeilisearchException
     */
    List<HashMap<String, Object>> searchGoodsByIndex(Integer pageNo, Integer pageSize, String searchWord) throws MeilisearchException;
}
