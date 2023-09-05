package com.xxx.xcx01_server.convert;

import com.xxx.xcx01_server.entity.CategoryEntity;
import com.xxx.xcx01_server.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 实体转换接口
 * componentModel = "spring" 生成的类上会加@Component注解 外部可以采用@Autowired方式注入Mapper
 */
@Mapper(componentModel = "spring")
public interface CategoryConvert {
//    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "sort",target = "sort")
    })
    CategoryVO convert(CategoryEntity entity);
    List<CategoryVO> convertList(List<CategoryEntity> list);
}
