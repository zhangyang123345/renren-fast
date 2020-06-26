package io.renren.modules.projects.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.projects.entity.ProjectEntity;

import java.util.Map;

public interface projectService extends IService<ProjectEntity> {

    /**
     * 查询试题
     * @param params
     * @return
     */
    public Map<String,Object> searchList(Map<String,Object> params);
}
