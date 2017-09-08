package com.springboot.hyll.config.common.base.controller;

import com.springboot.hyll.config.common.base.entity.QueryBase;
import com.springboot.hyll.config.common.base.service.BaseService;
import com.springboot.hyll.config.common.constant.SystemStaticConst;
import com.springboot.hyll.util.json.JsonHelper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* 类描述：通用的controller实现类
* @auther linzf
* @create 2017/8/23 0023 
*/
public abstract class BaseController<T extends QueryBase> {

    // 抽象方法
    protected abstract BaseService<T> getService();

    /**添加页面路径*/
    public final static String ADDPAGE = "/add";
    /**修改页面路径*/
    public final static String UPDATEPAGE = "/update";

    /**
     * Controller基路径
     * */
    protected String basePath;

    /**抽象方法，获取页面基路径
     * @throws Exception */
    protected String getPageBaseRoot() throws Exception{
        if(basePath==null){
            basePath = this.getClass().getName();
            Pattern p=Pattern.compile(".[a-z|A-z]+.controller.[a-z|A-z]+Controller");
            Matcher m=p.matcher(basePath);
            if(m.find()){
                basePath = m.group();
                basePath = basePath.substring(1, basePath.length()-10);
                basePath = basePath.toLowerCase();
                basePath = basePath.replace(".", "/");
                basePath = basePath.replace("/controller/", "/");
            }
            else{
                throw new Exception("获取页面基路径失败");
            }
        }
        return basePath;
    }


    /**
     * 功能描述：直接跳转到更新数据的页面
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updatePage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePage(T entity,Model model) throws Exception{
        entity = getService().get(entity);
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+UPDATEPAGE;
    }

    /** 跳转到添加对象页面
     * @throws Exception */
    @RequestMapping(value="/addPage")
    public String addPage() throws Exception{
        return getPageBaseRoot()+ADDPAGE;
    }

    /**
     * 功能描述：保存数据字典数据
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> save(T entity){
        entity = getService().save(entity);
        Map<String,Object> result = new HashMap<String, Object>();
        if(entity!=null){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"增加数据成功！");
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"增加数据失败！");
        }
        return result;
    }

    /**
     * 功能描述：更新数据字典数据
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> update(T entity){
        entity = getService().save(entity);
        Map<String,Object> result = new HashMap<String, Object>();
        if(entity!=null){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"更新数据成功！");
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"更新数据失败！");
        }
        return result;
    }

    /**
     * 功能描述：实现批量删除数据字典的记录
     * @param entity
     * @return
     */
    @RequestMapping(value = "/remove",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> remove(T entity){
        Map<String,Object> result = new HashMap<String, Object>();
        getService().remove(entity);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"删除数据成功！");
        return result;
    }


    /**
     * 功能描述：实现批量删除数据字典的记录
     * @param json
     * @return
     */
    @RequestMapping(value = "/removeBath",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> removeBath(String json){
        Map<String,Object> result = new HashMap<String, Object>();
        getService().removeBath((List<T>) JsonHelper.toList(json,(Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"删除数据成功！");
        return result;
    }

    /**
     * 功能描述：获取数据字典的分页的数据
     * @param entity
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> list(T entity){
        Map<String,Object> result = new HashMap<String, Object>();
        Page<T> baseList = getService().findByAuto(entity);
        result.put("totalCount",baseList.getTotalElements());
        result.put("result",baseList.getContent());
        return result;
    }

    /**
     * 功能描述：判断当前的字典元素是否已经存在
     * @param entity
     * @return
     */
    @RequestMapping(value = "/isExist",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> isExist(T entity){
        Map<String,Object> result = new HashMap<String, Object>();
        if(getService().get(entity)!=null){
            result.put("valid",false);
        }else{
            result.put("valid",true);
        }
        return result;
    }

}
