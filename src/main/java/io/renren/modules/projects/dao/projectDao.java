package io.renren.modules.projects.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.projects.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface projectDao extends BaseMapper<ProjectEntity> {

    /**
     * 查询试题
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询试题<总数>
     * @param params
     * @return
     */
    public int searchListCount(Map<String,Object> params);

}
