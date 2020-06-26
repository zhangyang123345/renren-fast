package io.renren.modules.question.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.question.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionDao extends BaseMapper<QuestionEntity> {
    /**
     * 查询试题
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询试题<总数>
     * @param params
     * @return
     */
    public int searchListCount(Map<String,Object> params);

    /**
     * 导入试题
     * @param params
     * @return
     */
    public Boolean  saveQuestions(List<Map<String,Object>> params);
}
