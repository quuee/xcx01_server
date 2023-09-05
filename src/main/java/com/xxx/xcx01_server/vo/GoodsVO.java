package com.xxx.xcx01_server.vo;


import com.xxx.xcx01_server.entity.GoodsSwiperEntity;

import java.math.BigDecimal;
import java.util.List;

public class GoodsVO {

    private Long id;

    private String goodsName;

    private BigDecimal price;

    private Integer stock;

    private String goodsImage;

    private Boolean hot;

    private String hotTime;

    private String categoryName;

    private String label;

    private Integer saleNum;

    private String goodsIntroducePage;

    private List<GoodsSwiperEntity> goodsSwiperList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public String getHotTime() {
        return hotTime;
    }

    public void setHotTime(String hotTime) {
        this.hotTime = hotTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getGoodsIntroducePage() {
        return goodsIntroducePage;
    }

    public void setGoodsIntroducePage(String goodsIntroducePage) {
        this.goodsIntroducePage = goodsIntroducePage;
    }

    public List<GoodsSwiperEntity> getGoodsSwiperList() {
        return goodsSwiperList;
    }

    public void setGoodsSwiperList(List<GoodsSwiperEntity> goodsSwiperList) {
        this.goodsSwiperList = goodsSwiperList;
    }
}
