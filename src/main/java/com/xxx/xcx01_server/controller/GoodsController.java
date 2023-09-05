package com.xxx.xcx01_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.xxx.xcx01_server.common.utils.PageResult;
import com.xxx.xcx01_server.common.utils.Result;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.service.GoodsService;
import com.xxx.xcx01_server.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 首页查询热门
     * @param pageNo
     * @param PageSize
     * @return
     */
    @RequestMapping(value = "indexHotList",method = RequestMethod.GET)
    public Result<PageResult<GoodsEntity>> indexHotList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "4")Integer PageSize){
        LambdaQueryWrapper<GoodsEntity> goodsEntityQueryWrapper = new LambdaQueryWrapper<>();
        Page<GoodsEntity> goodsEntityPage = new Page<>(pageNo, PageSize);
        goodsEntityQueryWrapper.eq(GoodsEntity::getHot,true);
        Page<GoodsEntity> page = goodsService.page(goodsEntityPage, goodsEntityQueryWrapper);
        return Result.ok(new PageResult<>(page.getRecords(),page.getTotal()));
    }

    /**
     * 分类页  按分类查询 且按标签分组
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "byCategoryGroupByLabel",method = RequestMethod.GET)
    public Result<Map<String, List<GoodsEntity>>> byCategoryGroupByLabel(@RequestParam()Long categoryId){
        Map<String, List<GoodsEntity>> stringListMap = goodsService.goodsListByCategoryGroupByLabel(categoryId);
        return Result.ok(stringListMap);
    }

    /**
     * 查看商品详情
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "detailIntroduce",method = RequestMethod.GET)
    public Result<GoodsVO> detailIntroduce(@RequestParam Long goodsId){
        GoodsVO goodsVO = goodsService.goodsDetailById(goodsId);
        return Result.ok(goodsVO);
    }

    /**
     * 搜索词联想，用于搜索框输入下拉模糊查询 自动联想
     * @param searchWord
     * @return
     */
    @RequestMapping(value = "searchWordAssociation",method = RequestMethod.GET)
    public Result<List<String>> searchWordAssociation(String searchWord){
        if(ObjectUtils.isEmpty(searchWord)){
            return Result.ok();
        }
        LambdaQueryWrapper<GoodsEntity> select = new LambdaQueryWrapper<GoodsEntity>().like(GoodsEntity::getGoodsName, searchWord).select(GoodsEntity::getGoodsName);
        List<GoodsEntity> list = goodsService.list(select);
        List<String> wordList = list.stream().map(GoodsEntity::getGoodsName).collect(Collectors.toList());

        return Result.ok(wordList);
    }

    /**
     * 搜索页面查询商品
     * @param pageNo
     * @param pageSize
     * @param searchWord
     * @return
     */
    @RequestMapping(value = "searchPageList",method = RequestMethod.GET)
    public Result<List<HashMap<String, Object>>> searchPageList(@RequestParam(defaultValue = "1")Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        String searchWord) throws MeilisearchException {
//        Page<GoodsVO> list = goodsService.searchGoods(pageNo,pageSize,searchWord);
        List<HashMap<String, Object>> hashMaps = goodsService.searchGoodsByIndex(pageNo, pageSize, searchWord);
        return Result.ok(hashMaps);
    }

}
