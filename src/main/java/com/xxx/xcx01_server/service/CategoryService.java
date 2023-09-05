package com.xxx.xcx01_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.xcx01_server.entity.CategoryEntity;
import com.xxx.xcx01_server.vo.CategoryVO;

import java.util.List;

public interface CategoryService extends IService<CategoryEntity> {
    List<CategoryVO> categoryPageList();
}
