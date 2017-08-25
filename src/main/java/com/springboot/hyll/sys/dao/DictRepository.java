package com.springboot.hyll.sys.dao;

import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.entity.Dict;

/**
 * 类描述：数据字典的dao操作类
 * @auther linzf
 * @create 2017/8/21 0021
 */
public interface DictRepository extends CustomRepository<Dict, Long> {

    /**
     * 功能描述：根据type和code且id不等于当前的字典数据的id来查询数据字典的数据
     * @param type
     * @param code
     * @param id
     * @return
     */
    Dict findByTypeAndCodeAndIdNot(String type,String code,Long id);

    /**
     * 功能描述：根据type和code来查询数据字典的数据
     * @param type
     * @param code
     * @return
     */
    Dict findByTypeAndCode(String type,String code);

}
