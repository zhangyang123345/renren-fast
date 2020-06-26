package io.renren.modules.lien_type.service.Impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.lien_type.dao.LineTypeDao;
import io.renren.modules.lien_type.entity.LineTypeEntity;
import io.renren.modules.lien_type.service.LineTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("LineTypeService")
public class LineTypeServiceImpl extends ServiceImpl<LineTypeDao, LineTypeEntity> implements LineTypeService {

    /**
     * 查询所有线别类型
     * @return
     */
    @Override
    public List<Map> search() {
        return baseMapper.searchList();
    }

    /**
     * 添加新的线别类型
     * @param params
     * @return
     */
    @Override
    public Boolean save(Map<String, Object> params) {
        return baseMapper.insertLine(params);
    }

    /**
     * 删除线别类型
     * @param params
     * @return
     */
    @Override
    public Boolean delete(Map<String, Object> params) {
        return baseMapper.deleteLine(params);
    }
}
