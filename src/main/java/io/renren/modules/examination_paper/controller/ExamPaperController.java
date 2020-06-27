package io.renren.modules.examination_paper.controller;

import io.renren.common.utils.PoiUtils;
import io.renren.common.utils.R;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.examination_paper.service.ExamPaperService;
import io.renren.modules.question.entity.QuestionEntity;
import io.renren.modules.question.service.QuestionService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exampaper")
public class ExamPaperController {

    @Autowired
    private ExamPaperService  examPaperService;

    @Autowired
    private QuestionService questionService;

    /**
     * 上传试卷信息
     * @param file
     * @return
     */
    @ResponseBody
    @Transactional
    @PostMapping("/import")
    public R uploadExam(MultipartFile file) {
        if (file != null) {
            Workbook book = null;
            File newFile = null;
            List<QuestionEntity> questionList = new ArrayList<>();
            QuestionEntity question = null;
            try {
                newFile = new PoiUtils().multipartFileToFile(file);
                book = new XSSFWorkbook(newFile);
            } catch (Exception ex) {
                try {
                    book = new HSSFWorkbook(new FileInputStream(newFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Sheet sheet = book.getSheetAt(0);
            ExampaperEntity paper = new ExampaperEntity();
            Row examRow = sheet.getRow(0);
            paper.setTitle(examRow.getCell(0).getStringCellValue());
            paper.setPtype(examRow.getCell(1).getStringCellValue());
            paper.setSscore(new Double(examRow.getCell(2).getNumericCellValue()).intValue());
            paper.setPscore(new Double(examRow.getCell(3).getNumericCellValue()).intValue());
            paper.setStime(new Double(examRow.getCell(4).getNumericCellValue()).intValue());
            examPaperService.saveExam(paper);
            int pid = paper.getPid();

            DecimalFormat format = new DecimalFormat("0");
            int RowNum = sheet.getLastRowNum();
            try {
            for (int i = 7; i < RowNum; i++) {
                Row row = sheet.getRow(i);
                String value1 ="";
                Cell tit = row.getCell(1);
                if(tit.getCellTypeEnum() == CellType.NUMERIC)value1 = new Double(tit.getNumericCellValue()).intValue()+"";
                else if(tit.getCellTypeEnum() == CellType.STRING)value1=tit.getStringCellValue();
                if(row.getCell(0) != null && row.getCell(0).getStringCellValue()!=null){
                    if(question!=null) questionList.add(question);
                    question = new QuestionEntity();
                    question.setPid(pid);
                    question.setQuestion(row.getCell(1).getStringCellValue()+",:,");
                    question.setType(row.getCell(2).getStringCellValue());
                    Cell ans = row.getCell(3);
                    if (ans.getCellTypeEnum() == CellType.NUMERIC)
                        question.setAnsower(new Double(ans.getNumericCellValue()).intValue() + "");
                    else if (ans.getCellTypeEnum() == CellType.STRING)
                        question.setAnsower(ans.getStringCellValue());
                   // question.setScore(new Double(row.getCell(4).getNumericCellValue()).intValue());
                } else if ("本题选项:".equals(value1)) {

                }else{
                    String  qString = question.getQuestion();

                    qString = qString + value1+"."+new PoiUtils().getValue(row.getCell(2),i,2,book,false)+",;,";

                    question.setQuestion(qString);
                }
            }
            book.close();
            }catch (NullPointerException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return R.error("部分试题答案为空，请检查！");
            }catch (IOException e) {
                e.printStackTrace();
            }
            //批量保存
//              Map<Integer, List<QuestionEntity>> batch = new BatchListUtil().batchList(questionList, 500);
            questionService.saveBatch(questionList,100);
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 查询试卷信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchExm(@RequestParam Map<String,Object> parma) {
        Map<String,Object> data =  examPaperService.searchList(parma);
        return R.ok().put("data",data);
    }

    /**
     * 查询试卷信息
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public R saveExm(@RequestBody ExampaperEntity parma) {
        examPaperService.saveExam(parma);
        return R.ok().put("pid",parma.getPid());
    }

    /**
     * 查询试卷信息<List>
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryExam")
    public R queryExam(ExampaperEntity params) {
        return R.ok().put("list", examPaperService.queryExam(params));
    }
}
