package io.renren.modules.examination_paper.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface ExamPaperService extends IService<ExampaperEntity> {
    /**
     *查询试卷信息
     * @return
     */
    public Map<String, Object> searchList(Map<String,Object> parma);

    /**
     * 获取历史试卷信息
     * @param params
     * @return
     */
    public List<Map<String,Object>> getHisList(Map<String,Object> params);
    /**
     * 查询试卷详细信息
     * @param params
     * @return
     */
    public Map getExam(ExampaperEntity params);

    /**
     * 保存试卷信息
     * @param entity
     * @return 试卷PID
     */
    @Transactional
    public int saveExam(ExampaperEntity entity);

    /**
     * 查询试卷信息<List>
     * @param params
     * @return
     */
    public List<Map> queryExam(ExampaperEntity params);
}
