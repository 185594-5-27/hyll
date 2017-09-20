package com.springboot.hyll.sys.service;

import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.dao.TreeRepository;
import com.springboot.hyll.sys.entity.Tree;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/*
* 类描述：菜单业务处理类
* @auther linzf
* @create 2017/9/19 0019 
*/
@Service
@Transactional(rollbackFor={IllegalArgumentException.class})
public class TreeService extends BaseService<Tree> {

    @Inject
    private TreeRepository treeRepository;

    @Override
    protected CustomRepository<Tree, Long> getRepository() {
        return treeRepository;
    }

    @Override
    public List<Tree> loadAll(Tree entity) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return treeRepository.findAll(sort);
    }
}
