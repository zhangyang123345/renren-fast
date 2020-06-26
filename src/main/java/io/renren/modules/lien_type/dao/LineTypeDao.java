package io.renren.modules.lien_type.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.lien_type.entity.LineTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LineTypeDao extends BaseMapper<LineTypeEntity> {

    /**
     * 查询线别
     * @return
     */
    public List<Map> searchList();

    /**
     * 新增线别类型
     * @param params
     * @return
     */
    public Boolean insertLine(Map<String,Object>params);

    /**
     * 删除线别类型
     * @param params
     * @return
     */
    public Boolean deleteLine(Map<String,Object>params);
}
