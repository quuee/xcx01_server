package com.xxx.xcx01_server.vo;

import com.xxx.xcx01_server.entity.GoodsEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 分类页面展示
 */

public class CategoryVO implements Serializable {

    private Long id;

    private String name;

    private Integer sort;

//    private List<GoodsEntity> goodsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
