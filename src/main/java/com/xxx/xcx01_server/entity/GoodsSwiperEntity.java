package com.xxx.xcx01_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_goods_swiper")
public class GoodsSwiperEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("swiper_image")
    private String swiperImage;

    @TableField
    private Integer sort;

    @TableField("goods_id")
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSwiperImage() {
        return swiperImage;
    }

    public void setSwiperImage(String swiperImage) {
        this.swiperImage = swiperImage;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}

