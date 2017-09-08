package com.springboot.hyll.config.common.base.service;

import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.config.customrepository.CustomRepository;
import com.springboot.hyll.sys.entity.Dict;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/*
* 类描述：通用的service实现类
* @auther linzf
* @create 2017/8/23 0023 
*/
public abstract class BaseService<T extends QueryBase> {

    protected abstract CustomRepository<T,Long> getRepository();

    /**
     * 功能描述：实现数据的分页查询
     * @param entity
     * @return
     */
    public Page<T> findByAuto(T entity){
        return getRepository().findByAuto(entity,this.getPageRequest(entity));
    }

    /**
     * 功能描述：根据id来获取数据
     * @param entity
     * @return
     */
    public T get(T entity){
        return getRepository().findOne(Example.of(entity));
    }

    /**
     * 功能描述：实现更新/保存数据
     * @param entity
     * @return
     */
    public T save(T entity){
        return getRepository().save(entity);
    }

    /**
     * 功能描述：根据实体来删除数据
     * @param entity
     */
    public void remove(T entity){
         getRepository().delete(entity);
    }

    /**
     * 功能描述：根据ID来批量删除数据
     * @param entityList
     */
    public void removeBath(List<T> entityList){
        getRepository().delete(entityList);
    }

    /**
     * 功能描述：加载所有的符合条件的数据
     * @param entity
     * @return
     */
    public List<T> loadAll(T entity){
        if(entity==null){
            return getRepository().findAll();
        }else{
            return getRepository().findByAuto(entity,new PageRequest(0,Integer.MAX_VALUE)).getContent();
        }
    }

    public PageRequest getPageRequest(T entity){
        PageRequest page = null;
        if(entity.getOrder()!=null&&entity.getSort()!=null&&!entity.getOrder().equals("")&&!entity.getSort().equals("")) {
            Sort sort = null;
            if (entity.getOrder().equalsIgnoreCase("desc")) {
                sort = new Sort(Sort.Direction.DESC, entity.getSort());
            } else {
                sort = new Sort(Sort.Direction.ASC, entity.getSort());
            }
            page = new PageRequest(entity.getPage()-1,entity.getLimit(),sort);
        }else{
            page = new PageRequest(entity.getPage()-1,entity.getLimit());
        }
        return page;
    }

}
