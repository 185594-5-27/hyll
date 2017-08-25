package com.springboot.hyll.sys.mapper;

import com.springboot.hyll.sys.dto.DictDTO;
import com.springboot.hyll.sys.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 类描述：实现数据的转换（http://mapstruct.org/）
 * Created by linzf on 2017/8/24 0024.
 */
@Mapper(componentModel = "spring")
public interface DictMapper {

    @Mapping(source = "id", target = "id")
    DictDTO dictToDictDTO(Dict dict);

    List<DictDTO> dictsToDictDTOs(List<Dict> dictList);

    DictMapper INSTANCE = Mappers.getMapper( DictMapper.class );
}
