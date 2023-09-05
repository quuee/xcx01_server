package com.xxx.xcx01_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.xcx01_server.entity.OrderEntity;
import com.xxx.xcx01_server.po.OrderParam;
import com.xxx.xcx01_server.vo.OrderVO;

import java.util.List;

public interface OrderService extends IService<OrderEntity> {

    /**
     * 创建订单
     * @param orderParam
     * @return
     */
    OrderVO createOrder(OrderParam orderParam);

    List<OrderVO> pageList(Integer pageNo,Integer pageSize,Integer orderStatus,String goodsName);

}
