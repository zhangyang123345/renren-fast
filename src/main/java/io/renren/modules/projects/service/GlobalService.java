package io.renren.modules.projects.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.projects.entity.GlobalEntity;

import java.util.List;
import java.util.Map;

public interface GlobalService extends IService<GlobalEntity> {
    /**
     * 获取Key 值，去重处理
     * @return
     */
    public List<Map> getKey();

    /**
     * 查询列表
     */
    public Map search(Map<String, Object> params);

    /**
     * 完成率图表
     * @param params
     * @return
     */
    public Map searchTable(Map<String, Object> params);

    /**
     * 导出报表
     * @param params
     * @return
     */
    public List<Map> listAll(Map<String, Object> params);
}
