package io.renren.modules.projects.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.projects.dao.ProjectDao;
import io.renren.modules.projects.entity.ProjectEntity;
import io.renren.modules.projects.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {

    /**
     * 查询试题信息
     * @param params
     * @return
     */
    @Override
    public Map<String,Object> searchList(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        int total = baseMapper.searchListCount(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        params.put("rows", rows);
        params.put("page", page);
//        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
//            params.put("sdate", dTime.split(",")[0]);
//            params.put("edate", dTime.split(",")[1]);
//        }
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.searchList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }

    /**
     * 查询案件数据
     * @param params
     * @return
     */
    public Map<String,Object> projectList(Map<String,Object> params) {
        Map<String, Object> data = new HashMap<>();
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        params.put("rows", rows);
        params.put("page", page);
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("start", dTime.split(",")[0]);
            params.put("end", dTime.split(",")[1]);
        }
        int total = baseMapper.projectListCount(params);
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.projectList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }


    /**
     * 获取案件ID以查重
     * @return
     */
    @Override
    public List<Map> getPj_id (){
        return baseMapper.getPj_id();
    }

    /**
     * 更新目标-达标信息
     */
    @Override
    public boolean updateTarget() {
        return baseMapper.updateTarget();
    }

    /**
     * 获取阶段完成数量及目标 BY  director
     * @param params
     * @return
     */
    @Override
    public Map queryLCount(Map<String, Object> params) {
        return baseMapper.queryLCount(params);
    }

    /**
     * 查询Top10 人员
     * @param params
     * @return
     */
    @Override
    public List<Map> queryTop(Map<String, Object> params) {
        return baseMapper.queryTop(params);
    }

    /**
     * 查询案件数量及类型比例
     * @param params
     * @return
     */
    @Override
    public List<Map> queryTypeRate(Map<String, Object> params) {
        return baseMapper.queryTypeRate(params);
    }

    /**
     * 状态占比
     * @param params
     * @return
     */
    @Override
    public List<Map> queryStateRate(Map<String, Object> params) {
        return baseMapper.queryStateRate(params);
    }
    /**
     * 查询详细案件信息
     * @param parmas
     * @return
     */
    @Override
    public List<Map> queryProject(Map<String, Object> parmas) {
        return baseMapper.queryProject(parmas);
    }

    /**
     * 查询个人案件信息 By  employee
     * @param params
     * @return
     */
    @Override
    public Map empPeoList(Map<String, Object> params) {
        Map data = new HashMap();
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        params.put("rows", rows);
        params.put("page", page);
        params.put("pageOffset",pageOffset);
        int total = baseMapper.empPeoCount(params);
        data.put("list",baseMapper.empPeoList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }
}

