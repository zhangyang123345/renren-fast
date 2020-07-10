package io.renren.modules.projects.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.projects.entity.ProjectDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDetailDao extends BaseMapper<ProjectDetailEntity> {

    /**
     * 批量更新detail 完成时间
     */
    public Boolean updateDetail(List<Map> params);
}
