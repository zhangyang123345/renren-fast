package io.renren.modules.position.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.position.dao.PositionDao;
import io.renren.modules.position.entity.PositionEntity;
import io.renren.modules.position.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("PositionService")
public class PositionServiceImpl  extends ServiceImpl<PositionDao, PositionEntity> implements PositionService {
    /**
     * 查询职称
     * @return
     */
    @Override
    public List<Map> search() {
        return baseMapper.search();
    }

    /**
     * 添加新的职称
     * @param params
     * @return
     */
    @Override
    public Boolean save(Map<String, Object> params) {
        return baseMapper.insert(params);
    }

    /**
     * 删除职称
     * @param params
     * @return
     */
    @Override
    public Boolean delete(Map<String, Object> params) {
        return baseMapper.delete(params);
    }
}
