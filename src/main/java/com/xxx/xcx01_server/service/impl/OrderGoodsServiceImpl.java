package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.xcx01_server.entity.OrderGoodsEntity;
import com.xxx.xcx01_server.mapper.OrderGoodsMapper;
import com.xxx.xcx01_server.service.OrderGoodsService;
import org.springframework.stereotype.Service;

@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoodsEntity> implements OrderGoodsService {
}
