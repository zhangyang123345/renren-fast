package io.renren.modules.ansower_sheet.controller;

import io.renren.common.utils.JsonUtil;
import io.renren.common.utils.PoiUtils;
import io.renren.common.utils.R;
import io.renren.modules.ansower_sheet.entity.AnsowerEntity;
import io.renren.modules.ansower_sheet.service.AnsowerService;
import io.renren.modules.employees.service.EmployeesService;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.examination_paper.service.ExamPaperService;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ansower")
public class AnsowerController {
    @Autowired
    private AnsowerService ansowerService;

    @Autowired
    private UnexampepolService unexampepolService;

    @Autowired
    private ExamPaperService examPaperService;

    @Autowired
    private EmployeesService employeesService;

    /**
     * 批量上传员工考试数据
     * @param file
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/import")
    public R uploadAnsower(MultipartFile file,@RequestParam Map  params) {
        if (file == null) return R.error();
        try {
            ExampaperEntity paper = new ExampaperEntity();
            JSONArray fileData = new PoiUtils().parseExcelFile( file, 1, 1);
            List<Map> ansData = JsonUtil.jsonToObject(fileData.toString(), new ArrayList<Map>().getClass());
            List<AnsowerEntity> ansowerList = new ArrayList<>();
            List<AnsowerEntity> updateList = new ArrayList<>();
            List<Integer> distinct = new ArrayList<>();
            int pid = Integer.parseInt(params.get("pid").toString());

            //所有员工考试数据----除重处理
            List<Map<String, Object>> examData = ansowerService.getAllExam(params);
            Map<String, AnsowerEntity> keyData = new HashMap<>();
            for (Map<String, Object> ma : examData) {
                AnsowerEntity data = new AnsowerEntity();
                data.setId(Integer.parseInt(ma.get("id").toString()));
                data.setJob_no(Integer.parseInt(ma.get("jobNo").toString()));
                data.setQid(ma.get("qid")!=null?Integer.parseInt(ma.get("qid").toString()):0);
                data.setPid(Integer.parseInt(ma.get("pid").toString()));
                data.setUscore(Integer.parseInt(ma.get("uscore").toString()));
                data.setStart_time(ma.get("startTime")!=null?ma.get("startTime").toString():"");
                data.setEnd_time(ma.get("endTime")!=null?ma.get("endTime").toString():"");
                data.setCreate_time(ma.get("createTime").toString());
                data.setAnsower(ma.get("ansower")!=null?ma.get("ansower").toString():"");
                keyData.put(ma.get("jobNo").toString(), data);
            }

            paper.setPid(pid);
            for (int i = 0, len = ansData.size(); i < len; i++) {
                AnsowerEntity data = new AnsowerEntity();
                Map adt = ansData.get(i);
                data.setPid(pid);
                String ansower = "";
                if(adt.get("score")!=null) data.setUscore(Integer.parseInt(adt.get("score").toString().split("\\.")[0]));
                if(adt.get("start")!=null)data.setStart_time(adt.get("start").toString());
                if(adt.get("finish")!=null)data.setEnd_time(adt.get("finish").toString());
                if(adt.get("Q1_A1_open")!=null) data.setJob_no(Integer.parseInt(adt.get("Q1_A1_open").toString().split("\\.")[0]));
                for (Object str : adt.keySet()) {
                    if (str.toString().contains("Q") && !str.toString().contains("Q1") && !str.toString().contains("Q2")) {
                        ansower += str.toString()+",:,"+adt.get(str).toString().split("\\.")[0]+",;,";
                    }
                }
                data.setAnsower(ansower);
                if (keyData.containsKey(String.valueOf(data.getJob_no()))) {
                    AnsowerEntity asd = keyData.get(String.valueOf(data.getJob_no()));
                    if (data.getUscore() > asd.getUscore()) {
                        data.setId(asd.getId());
                        data.setJob_no(asd.getJob_no());
                        data.setPid(asd.getPid());
                        data.setQid(asd.getQid());
                    }
                    updateList.add(data);
                } else if(distinct.contains(data.getJob_no())){
                    AnsowerEntity dist = ansowerList.get(distinct.indexOf(data.getJob_no()));
                    if(data.getUscore()>dist.getUscore()) ansowerList.set(distinct.indexOf(data.getJob_no()),data);
                } else {
                    ansowerList.add(data);
                    distinct.add(data.getJob_no());
                }
            }
            //批量保存
            if(ansowerList.size()>0)ansowerService.saveBatch(ansowerList,1000);
            if(updateList.size()>0) ansowerService.updateBatchById(updateList, 1000);
            //更新未参考人员名单
            unexampepolService.updateUnexam(paper);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("字段匹配错误！请检查");
        }
        return R.ok();
    }

    /**
     * 查询考试信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList(@RequestParam Map<String,Object> parma) {
        Map<String,Object> data =  ansowerService.searchList(parma);
        return R.ok().put("data",data);
    }

    /**
     * 查询主管下参考人员信息--List
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/searchListByDirector")
    public R searchListByDirector(@RequestBody Map<String,Object> params) {
        List<Map> data = ansowerService.searchListByDirector(params);
        return R.ok().put("data", data);
    }

    /**
     *查询主管下参考人员数目
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/searchCountByDirector")
    public R searchCountByDirector(@RequestBody Map<String,Object> params) {
        int count = ansowerService.searchCountByDirector(params);
        return R.ok().put("count", count);
    }

    /**
     * 合格人数
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryQualified")
    public R queryQualified(@RequestBody Map<String,Object> params) {
        int count = ansowerService.queryQualified(params);
        return R.ok().put("qualified", count);
    }


    /**
     * 获取各主管下员工数据《统计》
     * 最近一次
     * @return
     */
    @ResponseBody
    @PostMapping("/examDataByDirector")
    public R examDataByDirector(@RequestParam Map<String,Object> pdata) {
        Map params = new HashMap();
        String baseNo = pdata.get("jobNo").toString();
        List<Map<String, Object>> empData = employeesService.getWorkEmpl(pdata);
        List<Map> hisData = new ArrayList<>();
        List<Double> sort = new ArrayList<>();
        List<Integer> sortIndex = new ArrayList<>();
        List<Map> hisDataSort = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("0.0");
        int index = 0 ;
        for (Map<String,Object> it : empData) {
            params.put("jobNo", it.get("jobNo"));
            Map data = ansowerService.examDataByDirector(params);
            Double qualifR = (data.get("examCount") == null || Integer.parseInt(data.get("examCount").toString()) == 0) ? 0 : (Double.valueOf(data.get("qualified").toString()) / Double.valueOf(data.get("examCount").toString()));
            Double examR = (data.get("total") == null || Integer.parseInt(data.get("total").toString()) == 0) ? 0 : (Double.valueOf(data.get("examCount").toString()) / Double.valueOf(data.get("total").toString()));
            data.put("jobNo", it.get("jobNo"));
            data.put("name", it.get("name"));
            data.put("qualifRate", format.format(qualifR*100));
            data.put("examRate",format.format(examR*100));
            if("肖喜生".equals(it.get("name" ).toString())){data.put("name", "PVD厂");}
            if ( baseNo.equals(it.get("jobNo").toString() )) {
                hisDataSort.add(data);
            }else{
                sort.add(examR);
                sortIndex.add(index++);
                hisData.add(data);
            }
        }
        for(int i = sort.size() - 1; i >  0; i-- ){
            for (int j = sort.size()-1 ; j > 0 ; j--) {
                if(sort.get(j) > sort.get(j-1)){
                    Double cach = sort.get(j-1);
                    Integer cachId = sortIndex.get(j-1);
                    sort.set(j-1,sort.get(j));
                    sortIndex.set(j-1,sortIndex.get(j));
                    sort.set(j,cach);
                    sortIndex.set(j,cachId);
                }
            }
        }
        for (Integer i : sortIndex) {
            hisDataSort.add(hisData.get(i));
        }
        return R.ok().put("list", hisDataSort);
    }


    /**
     * 获取各主管下员工历史数据《统计》
     * 工号
     * @return
     */
    @ResponseBody
    @PostMapping("/hisDataByDirector")
    public R hisDataByDirector(@RequestParam Map<String,Object> requData) {
        Map params = new HashMap();
        params.put("rows", 10);
        params.put("page", 1);
        params.put("pageOffset", 0);
        params.put("order","create_time");
        params.put("asc", "asc");
        String job_no = requData.get("jobNo")!=null?requData.get("jobNo").toString():"";
        List<String> qualifRate = new ArrayList<>();
        List<String> examRate = new ArrayList<>();
        List<String>  name = new ArrayList<>();
        List<Map> examData = examPaperService.getHisList(params);
        DecimalFormat format = new DecimalFormat("0.00");
        for (Map exam : examData) {
            exam.put("jobNo", job_no);
            Map ansData = ansowerService.examDataByDirector(exam);
//            exam.put("unExamCount", ansData.get("unExamCount"));
//            exam.put("qualified", ansData.get("qualified"));
//            exam.put("total", ansData.get("total"));
            Double qualifR = (ansData.get("examCount") == null || Integer.parseInt(ansData.get("examCount").toString()) == 0) ? 0 : (Double.valueOf(ansData.get("qualified").toString()) / Double.valueOf(ansData.get("examCount").toString()));
            Double examR = (ansData.get("total") == null || Integer.parseInt(ansData.get("total").toString()) == 0) ? 0 : (Double.valueOf(ansData.get("examCount").toString()) / Double.valueOf(ansData.get("total").toString()));
            name.add(exam.get("createTime").toString().split(" ")[0]);
            qualifRate.add(format.format(qualifR*100));
            examRate.add(format.format(examR*100));
        }
        params.clear();
        params.put("time", name);
        params.put("qualifRate", qualifRate);
        params.put("examRate", examRate);
        return R.ok().put("data", params);
    }
}
