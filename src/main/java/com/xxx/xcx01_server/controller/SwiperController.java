package com.xxx.xcx01_server.controller;

import com.xxx.xcx01_server.common.utils.Result;
import com.xxx.xcx01_server.entity.SwiperEntity;
import com.xxx.xcx01_server.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/swiper")
@RestController
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    /**
     * 首页查询 轮播图
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Result<List<SwiperEntity>> list(){
        List<SwiperEntity> list = swiperService.list();
        return Result.ok(list);
    }

}
