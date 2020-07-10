package io.renren.modules.projects.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.projects.entity.ProjectDetailEntity;

import java.util.List;
import java.util.Map;

public interface ProjectDetailService  extends IService<ProjectDetailEntity> {
    /**
     * 批量更新detail 完成时间
     */
    public Boolean updateDetail(List<Map> params);
}
