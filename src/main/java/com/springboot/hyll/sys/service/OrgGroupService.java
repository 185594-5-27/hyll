package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.dao.OrgGroupRepository;
import com.springboot.hyll.sys.entity.OrgGroup;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/*
* 类描述：
* @auther linzf
* @create 2017/8/30 0030 
*/
@Service
@Transactional(rollbackFor={IllegalArgumentException.class})
public class OrgGroupService extends BaseService<OrgGroup> {

    @Inject
    private OrgGroupRepository orgGroupRepository;

    @Override
    protected CustomRepository<OrgGroup, Long> getRepository() {
        return orgGroupRepository;
    }

    @Override
    public List<OrgGroup> loadAll(OrgGroup entity) {
        Sort sort = new Sort(Sort.Direction.ASC, "node");
        return orgGroupRepository.findAll(sort);
    }
}
