package io.renren.modules.examination_paper.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamPaperDao extends BaseMapper<ExampaperEntity> {

    /**
     * 查询试卷信息
     * @param params 查询参数
     * @return
     */
    public List<Map<String,Object>> searchList(Map<String,Object> params);
    /**
     * 查询试卷信息<总数/>
     * @param params 查询参数
     * @return
     */
    public int searchListCount(Map<String,Object> params);

    /**
     * 插入试卷信息
     * @param params
     * @return
     */
    public int saveExam(ExampaperEntity params);

    /**
     * 查询试卷详细信息
     * @param params
     * @return
     */
    public Map getExam(ExampaperEntity params);
}
