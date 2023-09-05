package com.xxx.xcx01_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.xcx01_server.common.utils.Result;
import com.xxx.xcx01_server.entity.CategoryEntity;
import com.xxx.xcx01_server.service.CategoryService;
import com.xxx.xcx01_server.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 首页查询
     * @return
     */
    @RequestMapping(value = "indexList",method = RequestMethod.GET)
    public Result<List<CategoryEntity>> indexPageList(){
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CategoryEntity::getSort);
        List<CategoryEntity> list = categoryService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 分类页面查询
     * @return
     */
    @RequestMapping(value = "categoryPageList",method = RequestMethod.GET)
    public Result<List<CategoryVO>> categoryPageList(){
        List<CategoryVO> list = categoryService.categoryPageList();
        return Result.ok(list);
    }
}
