package io.renren;

import io.renren.modules.ansower_sheet.service.AnsowerService;
import io.renren.modules.employees.service.EmployeesService;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.examination_paper.service.ExamPaperService;
import io.renren.modules.lien_type.service.LineTypeService;
import io.renren.modules.position.service.PositionService;
import io.renren.modules.question.service.QuestionService;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    @Autowired
    private PositionService positionService;

    @Autowired
    private LineTypeService lineTypeService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamPaperService examPaperService;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AnsowerService ansowerService;

    @Autowired
    private UnexampepolService unexampepolService;

    private MockMvc mockmvc;
//    @Test
//    public void test() {
//        Map<String,Object> datamap = new HashMap<>();
//        List<Map> data = positionService.search();
//        for(Map da:data){
//            System.out.println("position------>"+da.get("plant_type"));
//        }
//
//        List<Map> ldata = lineTypeService.search();
//        for(Map da:ldata){
//            System.out.println("line------>"+da.get("line_type"));
//        }
//
//        datamap.put("rows", 10);
//        datamap.put("page", 1);
//        Map qdata = questionService.searchList(datamap);
//        Map pdata = examPaperService.searchList(datamap);
//        Map edata = employeesService.searchList(datamap);
//        System.out.println("Q------>"+qdata.get("total"));
//        System.out.println("P------>"+pdata.get("total"));
//        System.out.println("E------>"+edata.get("total"));
//    }

    /**上传数据测试*/
    @Test
    public void testImport() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ExampaperEntity paper = new ExampaperEntity();
        paper.setPscore(60);
        paper.setPtype("医疗防护");
        paper.setStime(10);
        paper.setSscore(100);
        paper.setTitle("新冠医疗知识");
//        EmployeesEntity paper = new EmployeesEntity();
//        paper.setJob_no(2480579);
//        paper.setName("牟秋霞");
//        paper.setPj_name("陈名平");
//        paper.setPhone("13428063741");
//        paper.setEmail("www.baidu.com");
//        paper.setEntry_date("2020-06-10");
//        paper.setDirector("陈名平");
//        paper.setLine_type("SME");
//        paper.setWclass("A7");
//        paper.setPlant_type("PVD");
//        paper.setOffice_location("D4-2f");
//        paper.setPosition("工程师I");
//        Map<String,Object> params = new HashMap();
//        params.put("title", "医疗");
        MvcResult result = mockmvc.perform(
                post("/ansower/examDataByDirector")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
        )// .content(JSON.toJSONString(paper)))
         .andReturn();
        System.out.println(result.getResponse().getContentAsString());

//        unexampepolService.updateUnexam(paper);


    }
}
