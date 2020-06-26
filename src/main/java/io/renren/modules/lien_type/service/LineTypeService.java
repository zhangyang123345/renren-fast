package io.renren.modules.lien_type.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.lien_type.entity.LineTypeEntity;

import java.util.List;
import java.util.Map;

public interface LineTypeService extends IService<LineTypeEntity> {

    /**
     * 查询所有线别类型
     * @return
     */
    public List<Map> search();

    /**
     * 添加新的线别类型
     * @param params
     * @return
     */
    public Boolean save(Map<String,Object> params);

    /**
     * 删除线别类型
     * @param params
     * @return
     */
    public Boolean delete(Map<String,Object> params);
}
