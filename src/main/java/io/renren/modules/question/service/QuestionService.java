package io.renren.modules.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.question.entity.QuestionEntity;

import java.util.List;
import java.util.Map;


public interface QuestionService extends IService<QuestionEntity> {

    /**
     * 查询试题
     * @param params
     * @return
     */
    public Map<String,Object>  searchList(Map<String,Object> params);

    /**
     * 导入试题
     * @param params
     * @return
     */
    public Boolean  saveQuestions(List<Map<String,Object>> params);
}
