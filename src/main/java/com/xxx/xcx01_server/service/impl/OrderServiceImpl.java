package com.xxx.xcx01_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.xcx01_server.common.utils.SnowFlake;
import com.xxx.xcx01_server.convert.OrderConvert;
import com.xxx.xcx01_server.convert.OrderGoodsConvert;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.entity.OrderEntity;
import com.xxx.xcx01_server.entity.OrderGoodsEntity;
import com.xxx.xcx01_server.mapper.OrderMapper;
import com.xxx.xcx01_server.po.OrderGoodsParam;
import com.xxx.xcx01_server.po.OrderParam;
import com.xxx.xcx01_server.service.GoodsService;
import com.xxx.xcx01_server.service.OrderGoodsService;
import com.xxx.xcx01_server.service.OrderService;
import com.xxx.xcx01_server.vo.OrderGoodsVO;
import com.xxx.xcx01_server.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {



    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderConvert orderConvert;

    @Autowired
    private OrderGoodsConvert orderGoodsConvert;

    @Transactional
    @Override
    public OrderVO createOrder(OrderParam orderParam) {

        OrderEntity orderEntity = new OrderEntity();
        SnowFlake snowFlake = new SnowFlake(1,1);

        orderEntity.setOrderNo(String.valueOf(snowFlake.nextId()));
        orderEntity.setCreateTime(new Date());
        orderEntity.setPayWay(orderParam.getPayWay());

        BigDecimal paySumMoney = new BigDecimal("0.01"); //总支付金额

        List<OrderGoodsEntity> orderGoodsEntities = new ArrayList<>();

        for (OrderGoodsParam goodsParam : orderParam.getGoodsList()) {
            GoodsEntity goods = goodsService.getById(goodsParam.getGoodsId());
            BigDecimal money = goods.getPrice().multiply(new BigDecimal(goodsParam.getGoodsNum()));
            paySumMoney = paySumMoney.add(money);

            OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
            orderGoodsEntity.setOrderNo(orderEntity.getOrderNo());
            orderGoodsEntity.setGoodsId(goodsParam.getGoodsId());
            orderGoodsEntity.setGoodsName(goods.getGoodsName());
            orderGoodsEntity.setGoodsImage(goods.getGoodsImage());
            orderGoodsEntity.setNum(goodsParam.getGoodsNum());
            orderGoodsEntity.setPrice(goods.getPrice());
            orderGoodsEntities.add(orderGoodsEntity);
        }

        orderEntity.setPayMoney(paySumMoney);
        orderEntity.setReviceAddress(orderParam.getReviceAddress().toString());

        boolean save = save(orderEntity);
        if(save){
            orderGoodsService.saveBatch(orderGoodsEntities);
        }

        OrderVO orderVO = orderConvert.convert(orderEntity);
        List<OrderGoodsVO> orderGoodsVOS = orderGoodsConvert.convertList(orderGoodsEntities);
        orderVO.setGoodsList(orderGoodsVOS);
        return orderVO;

    }

    @Override
    public List<OrderVO> pageList(Integer pageNo, Integer pageSize, Integer orderStatus, String goodsName) {
        Page<OrderEntity> orderEntityPage = new Page<>(pageNo, pageSize);
        List<OrderEntity> orderEntities = baseMapper.selectOrderPageList(orderEntityPage, orderStatus, goodsName);

        List<OrderVO> orderVOS = orderConvert.convertList(orderEntities);
        for (OrderVO orderVO : orderVOS) {
            LambdaQueryWrapper<OrderGoodsEntity> eq = new LambdaQueryWrapper<OrderGoodsEntity>().eq(OrderGoodsEntity::getOrderNo, orderVO.getOrderNo());
            List<OrderGoodsEntity> goodsEntities = orderGoodsService.list(eq);
            List<OrderGoodsVO> orderGoodsVOS = orderGoodsConvert.convertList(goodsEntities);
            orderVO.setGoodsList(orderGoodsVOS);
        }
        return orderVOS;
    }
}
