package com.xxx.xcx01_server.convert;

import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.vo.GoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsConvert {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "goodsName",target = "goodsName"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "stock",target = "stock"),
            @Mapping(source = "goodsImage",target = "goodsImage"),
            @Mapping(source = "hot",target = "hot"),
            @Mapping(source = "hotTime",target = "hotTime"),
            @Mapping(source = "categoryName",target = "categoryName"),
            @Mapping(source = "label",target = "label"),
            @Mapping(source = "saleNum",target = "saleNum"),
            @Mapping(source = "goodsIntroducePage",target = "goodsIntroducePage"),
    })
    GoodsVO convert(GoodsEntity entity);
    List<GoodsVO> convertList(List<GoodsEntity> list);
}
