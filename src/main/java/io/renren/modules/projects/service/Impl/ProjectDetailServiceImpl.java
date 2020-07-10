package io.renren.modules.projects.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.projects.dao.ProjectDetailDao;
import io.renren.modules.projects.entity.ProjectDetailEntity;
import io.renren.modules.projects.service.ProjectDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("ProjectDetailService")
public class ProjectDetailServiceImpl  extends ServiceImpl<ProjectDetailDao, ProjectDetailEntity> implements ProjectDetailService {
    /**
     * 批量更新detail 完成时间
     */
    @Transactional
    public Boolean updateDetail(List<Map> params) {
        return baseMapper.updateDetail(params);
    }
}
