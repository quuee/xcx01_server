package com.xxx.xcx01_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.xcx01_server.entity.OrderEntity;
import com.xxx.xcx01_server.vo.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderEntity> {

    List<OrderEntity> selectOrderPageList(IPage<OrderEntity> page, @Param("orderStatus") Integer orderStatus, @Param("goodsName")String goodsName);
}
