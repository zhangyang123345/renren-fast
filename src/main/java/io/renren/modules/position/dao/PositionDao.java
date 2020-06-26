package io.renren.modules.position.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.position.entity.PositionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PositionDao extends BaseMapper<PositionEntity> {
    /**
     * 查询所有职称
     * @return
     */
    public List<Map> search();

    /**
     * 添加新的职称
     * @param params
     * @return
     */
    public Boolean insert(Map<String,Object> params);

    /**
     * 删除职称
     * @param params
     * @return
     */
    public Boolean delete(Map<String,Object> params);
}
