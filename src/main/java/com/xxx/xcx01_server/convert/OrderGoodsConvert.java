package com.xxx.xcx01_server.convert;

import com.xxx.xcx01_server.entity.OrderGoodsEntity;
import com.xxx.xcx01_server.vo.OrderGoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderGoodsConvert {

    @Mappings({
            @Mapping(source = "goodsId",target = "goodsId"),
            @Mapping(source = "goodsName",target = "goodsName"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "goodsImage",target = "goodsImage"),
            @Mapping(source = "num",target = "num"),
    })
    OrderGoodsVO convert(OrderGoodsEntity entity);
    List<OrderGoodsVO> convertList(List<OrderGoodsEntity> list);
}
