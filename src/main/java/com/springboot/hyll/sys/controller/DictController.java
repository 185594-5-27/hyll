package com.springboot.hyll.sys.controller;

import com.springboot.hyll.config.common.base.controller.BaseController;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.sys.entity.Dict;
import com.springboot.hyll.sys.service.DictService;
import com.springboot.hyll.util.dict.DictCache;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：数据字典的controller层
* @auther linzf
* @create 2017/8/21 0021 
*/
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController<Dict> {

    @Inject
    private DictService dictService;

    @Override
    protected BaseService<Dict> getService() {
        return dictService;
    }

    /**
     * 功能描述：重新加载数据字典的数据到内存中
     * @return
     */
    @RequestMapping(value = "/reload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> reload(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Dict> dictList = dictService.loadAll(null);
        DictCache.reload(dictList);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"重新加载数据字典成功！");
        return result;
    }

}
