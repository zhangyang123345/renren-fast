package io.renren.modules.projects.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.projects.dao.projectDao;
import io.renren.modules.projects.entity.ProjectEntity;
import io.renren.modules.projects.service.projectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("projectService")
public class projectServiceImpl extends ServiceImpl<projectDao, ProjectEntity> implements projectService {

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
        if(params.get("rows")!=null) params.put("rows", Integer.parseInt(params.get("rows").toString()));
        if(params.get("page")!=null) params.put("page", Integer.parseInt(params.get("page").toString()));
        if(params.get("rows")==null) params.put("rows", 10);
        if(params.get("page")==null) params.put("page", 1);
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("sdate", dTime.split(",")[0]);
            params.put("edate", dTime.split(",")[1]);
        }
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.searchList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }
}
