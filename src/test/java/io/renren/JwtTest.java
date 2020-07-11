package io.renren;

import io.renren.modules.ansower_sheet.service.AnsowerService;
import io.renren.modules.employees.service.EmployeesService;
import io.renren.modules.examination_paper.service.ExamPaperService;
import io.renren.modules.lien_type.service.LineTypeService;
import io.renren.modules.position.service.PositionService;
import io.renren.modules.question.service.QuestionService;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


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
    @Test
    public void test() {
        for (int i = 0; i < 80; i++) {
            System.out.println("insert into project_target(job_no,cost_category,target_date,separtment)(select job_no,cost_category,date_format(date_sub('2019-01-07',INTERVAL -"+7*i+" DAY),'%Y-%m-%d') target_date,separtment   from employee where  cost_category = 'IL' and position not like 'Operator%'  and active = 1) ;");
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("insert into project_target(job_no,cost_category,target_date,separtment)(select job_no,cost_category,date_format(date_sub('2019-01-16',INTERVAL -"+i+" MONTH),'%Y-%m-%16') target_date,separtment   from employee where  cost_category = 'DL' and position not like 'Operator%'  and active = 1) ;");
        }
    }

    /**上传数据测试*/
    @Test
    public void testImport() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        ExampaperEntity paper = new ExampaperEntity();
//        paper.setPscore(60);
//        paper.setPtype("医疗防护");
//        paper.setStime(10);
//        paper.setSscore(100);
//        paper.setTitle("新冠医疗知识");
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
//        params.put("name","岳平");
//        MvcResult result = mockmvc.perform(
//                post("/project/listAll")
//                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        )//.content(JSON.toJSONString(params)))
//         .andReturn();
//        System.out.println(result.getResponse().getContentAsString());

//        unexampepolService.updateUnexam(paper);


    }
}
