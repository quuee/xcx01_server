package com.xxx.xcx01_server.convert;

import com.xxx.xcx01_server.entity.OrderEntity;
import com.xxx.xcx01_server.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConvert {

    @Mappings({
            @Mapping(source = "orderNo",target = "orderNo"),
            @Mapping(source = "orderStatus",target = "orderStatus"),
            @Mapping(source = "createTime",target = "createTime"),
            @Mapping(source = "payMoney",target = "payMoney"),
            @Mapping(source = "reviceAddress",target = "reviceAddress"),
            @Mapping(source = "expressCompany",target = "expressCompany"),
            @Mapping(source = "expressNo",target = "expressNo"),
            @Mapping(source = "payWay",target = "payWay"),
    })
    OrderVO convert(OrderEntity entity);
    List<OrderVO> convertList(List<OrderEntity> list);
}
