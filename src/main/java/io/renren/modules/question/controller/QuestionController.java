package io.renren.modules.question.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    /**
     * 上传试题数据
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/import")
    public R uploadExam(MultipartFile file) {
        if (file == null) return R.error();
        //TODO试卷试题导入
        return R.ok();
    }

    /**
     * 查询试题信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchExm(@RequestParam Map<String,Object> parma) {
        Map<String,Object> data =  questionService.searchList(parma);
        return R.ok().put("data",data);
    }
}
