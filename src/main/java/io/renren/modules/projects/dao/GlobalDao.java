package io.renren.modules.projects.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.projects.entity.GlobalEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GlobalDao  extends BaseMapper<GlobalEntity> {

    /**
     * 获取Key 值，去重处理
     * @return
     */
    public List<Map> getKey();


    /**
     * 查询列表
     */
    public List<Map> search(Map<String, Object> params);
    /**
     * 查询列表
     */
    public int searchCount(Map<String, Object> params);

    /**
     * 完成率图表
     * @param params
     * @return
     */
    public List<Map> searchTable(Map<String, Object> params);

    /**
     * 导出报表
     * @param params
     * @return
     */
    public List<Map> listAll(Map<String, Object> params);
}
