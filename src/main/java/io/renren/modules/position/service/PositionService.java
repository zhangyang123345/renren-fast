package io.renren.modules.position.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.position.entity.PositionEntity;

import java.util.List;
import java.util.Map;

public interface PositionService extends IService<PositionEntity> {
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
    public Boolean save(Map<String,Object> params);

    /**
     * 删除职称
     * @param params
     * @return
     */
    public Boolean delete(Map<String,Object> params);
}
