package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.dao.DictRepository;
import com.springboot.hyll.sys.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
* 类描述：数据字典业务处理类
* @auther linzf
* @create 2017/8/21 0021 
*/
@Service
@Transactional(rollbackFor={IllegalArgumentException.class})
public class DictService extends BaseService<Dict> {

    @Inject
    private DictRepository dictRepository;

    @Override
    protected CustomRepository<Dict,Long> getRepository() {
        return dictRepository;
    }

    /**
     * 功能描述：根据type和code且id不等于当前的字典数据的id来查询数据字典的数据
     * @param type
     * @param code
     * @param id
     * @return
     */
    public Dict findByTypeAndCodeAndIdNot(String type,String code,Long id){
        return dictRepository.findByTypeAndCodeAndIdNot(type,code,id);
    }

    /**
     * 功能描述：根据type和code来查询数据字典的数据
     * @param type
     * @param code
     * @return
     */
    public Dict findByTypeAndCode(String type,String code){
        return dictRepository.findByTypeAndCode(type,code);
    }

}
