package com.xxx.xcx01_server.controller;

import com.xxx.xcx01_server.common.utils.Result;
import com.xxx.xcx01_server.po.OrderParam;
import com.xxx.xcx01_server.service.OrderService;
import com.xxx.xcx01_server.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result<OrderVO> createOrder(@RequestBody OrderParam orderParam){
        OrderVO order = orderService.createOrder(orderParam);
        return Result.ok(order);
    }

    @RequestMapping(value = "pageList",method = RequestMethod.GET)
    public Result<List<OrderVO>> orderPageList(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer PageSize,
                                               @RequestParam(defaultValue = "0") Integer orderType,
                                               String goodsName){
        List<OrderVO> orderVOList = new ArrayList<>(0);
        if(orderType==0){
            orderVOList = orderService.pageList(pageNo,PageSize,0,goodsName);
        }else {
            orderVOList = orderService.pageList(pageNo,PageSize,orderType,goodsName);
        }

        return Result.ok(orderVOList);

    }
}
