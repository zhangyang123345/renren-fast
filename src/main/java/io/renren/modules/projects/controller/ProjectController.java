package io.renren.modules.projects.controller;

import io.renren.common.utils.JsonUtil;
import io.renren.common.utils.PoiUtils;
import io.renren.common.utils.R;
import io.renren.modules.employees.service.EmployeesService;
import io.renren.modules.projects.entity.ProjectDetailEntity;
import io.renren.modules.projects.entity.ProjectEntity;
import io.renren.modules.projects.service.ProjectDetailService;
import io.renren.modules.projects.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectDetailService projectDetailService;
    @Autowired
    private EmployeesService employeesService;

    /**
     * 查询人员下案件信息
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList(@RequestParam Map<String,Object> params) {
        return R.ok().put("data", projectService.searchList(params));
    }
    /**
     * 案件信息
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/project")
    public R projectList(@RequestParam Map<String,Object> params) {
        return R.ok().put("data", projectService.projectList(params));
    }

    /**
     * 导入数据
     * @param file
     * @return
     */
    @ResponseBody
    @Transactional
    @PostMapping("/import")
    public R upload(MultipartFile file) {
        if (file == null) return R.error("文件异常！");
        try {
            JSONArray fileData = new PoiUtils().parseExcelFile(file, 1, 1);
            List<Map> ansData = JsonUtil.jsonToObject(fileData.toString(), new ArrayList<Map>().getClass());

            //获取原数据
            List<Map> cellDate = projectService.getPj_id();
            Map<String, Object> cellKey = new HashMap();
            for (Map mp: cellDate) {
                cellKey.put(mp.get("projectId").toString(), mp.get("id"));
            }

            List<ProjectEntity> inserList = new ArrayList();
            List<ProjectEntity> updateList = new ArrayList();
            List<ProjectDetailEntity> insDList = new ArrayList();
            List<Map> updatDList = new ArrayList();
            Map<String, Map> target = new HashMap<>();
            //处理导入数据
            for (Map mp : ansData) {
                ProjectEntity project = new ProjectEntity();
                int projectId = mp.get("Project Id")!=null?Double.valueOf(mp.get("Project Id").toString()).intValue():null;
                project.setProject_id(projectId);
                project.setProject_name(mp.get("Project Name")!=null?mp.get("Project Name").toString():"");
                project.setProject_type(mp.get("Project Type")!=null?mp.get("Project Type").toString():"");
                project.setState(mp.get("State")!=null?mp.get("State").toString():"");
                project.setLsc_date(mp.get("Date of Last State Change")!=null?mp.get("Date of Last State Change").toString():"");
                project.setSubmit_date(mp.get("Submitted Date")!=null?mp.get("Submitted Date").toString():"");
                project.setSubmit_email(mp.get("Submitted By Email Address")!=null?mp.get("Submitted By Email Address").toString():"");
                project.setProcess_improve(mp.get("Process Improved")!=null?mp.get("Process Improved").toString():"");
                project.setProcess_email(mp.get("Approver Email Address")!=null?mp.get("Approver Email Address").toString():"");
                project.setTeam_leader(mp.get("Team Leader")!=null?mp.get("Team Leader").toString():"");
                project.setLeader_email(mp.get("Team Leader Email")!=null?mp.get("Team Leader Email").toString():"");
                project.setCustomer_field(mp.get("Custom Field 1")!=null?mp.get("Custom Field 1").toString():"");
                if(mp.get("Closed Date")!=null)project.setClose_date(mp.get("Closed Date").toString());
                if(mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString()))project.setNntid(Double.valueOf(mp.get("Non NT ID Team Member 1").toString()).intValue());

                String teamMember = "" ;
                String memberEmail = "" ;
                int membIndex = 1 ;
                while (mp.get("Team Member " + membIndex) != null && StringUtils.isNotEmpty(mp.get("Team Member " + membIndex).toString())) {
                    teamMember += mp.get("Team Member " + membIndex).toString() + ",";
                    memberEmail += mp.get("Team Member " + membIndex++ +" Email Address").toString() + ",";
                }
                if(teamMember.length()>1)teamMember = teamMember.substring(0, teamMember.length() - 1);
                if(memberEmail.length()>1)memberEmail = memberEmail.substring(0, memberEmail.length() - 1);
                project.setTeam_member(teamMember);
                project.setMember_email(memberEmail);
                if (cellKey.containsKey(String.valueOf(projectId))) {
                    project.setId(Integer.parseInt(cellKey.get(String.valueOf(projectId)).toString()));
                    updateList.add(project);
                    if (mp.get("Closed Date") != null &&  StringUtils.isNotEmpty(mp.get("Closed Date").toString())) {
                        Map detail = new HashMap();
                        detail.put("projectId", mp.get("Project Id").toString());
                        detail.put("closeDate", mp.get("Closed Date").toString());
                        updatDList.add(detail);
                    }
                } else {
                    String[] email = memberEmail.split(",");
                    ProjectDetailEntity detailEntity = null;
                    for (String str : email) {
                        detailEntity = new ProjectDetailEntity();
                        detailEntity.setMember_email(str);
                        detailEntity.setProject_id(projectId);
                        detailEntity.setProject_type(mp.get("Project Type")!=null?mp.get("Project Type").toString():"");
                        detailEntity.setClose_date(mp.get("Closed Date")!=null?mp.get("Closed Date").toString():"");
                        insDList.add(detailEntity);
                    }
                    detailEntity = new ProjectDetailEntity();
                    detailEntity.setProject_id(projectId);
                    detailEntity.setProject_type(mp.get("Project Type")!=null?mp.get("Project Type").toString():"");
                    detailEntity.setClose_date(mp.get("Closed Date")!=null?mp.get("Closed Date").toString():"");
                    if(mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString()))detailEntity.setNntid(Double.valueOf(mp.get("Non NT ID Team Member 1").toString()).intValue());
                    insDList.add(detailEntity);
                    inserList.add(project);
                }
            }
//            更新数据
            if(inserList.size()>0) projectService.saveBatch(inserList, 1000);
            if(updateList.size()>0) projectService.updateBatchById(updateList, 500);
            if(insDList.size()>0) projectDetailService.saveBatch(insDList, 1000);
            if(updatDList.size()>0) projectDetailService.updateDetail(updatDList);
            //处理达标数据
            projectService.updateTarget();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return R.error("数据处理异常，请检查导入数据！");
        }
        return R.ok();
    }

    /**
     * 查询改善数量及完成率 By Director
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryLTable")
    public R queryLTable(@RequestParam Map<String, Object> params) {
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("start", dTime.split(",")[0]);
            params.put("end", dTime.split(",")[1]);
        }
        Map<String, Object> data = new HashMap<>();
        DecimalFormat format = new DecimalFormat("0.0");
        DecimalFormat countf = new DecimalFormat("0");
        List<Map<String,Object>> emp =  employeesService.getWorkEmpl(params);
        List name = new ArrayList();
        List count = new ArrayList();
        List target = new ArrayList();
        List rate = new ArrayList();
        for (Map<String, Object> mp : emp) {
            mp.put("start", params.get("start"));
            mp.put("end", params.get("end"));
            mp.put("costCategory", params.get("costCategory")!=null?params.get("costCategory"):"IL");
            Map cdata = projectService.queryLCount(mp);
            if ("肖喜生".equals(mp.get("name").toString())) {
                name.add("PVD厂");
            }else{
                name.add(mp.get("name").toString());
            }
            String  coun = cdata.get("count").toString();
            String  tage = cdata.get("target").toString();
            count.add(countf.format(coun));
            target.add(countf.format(tage));
            rate.add(format.format(Double.valueOf(coun)/Double.valueOf(tage)));
        }
        data.put("name", name.toArray());
        data.put("count", count.toArray());
        data.put("target", target.toArray());
        data.put("rate", rate.toArray());
        return R.ok().put("data", data);
    }

    /**
     * 查询Top10 人员
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryTop")
    public R queryTop(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", projectService.queryTop(params));
    }

    /**
     * 查询案件数量及类型比例
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryTypeRate")
    public R queryTypeRate(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", projectService.queryTypeRate(params));
    }

    /**
     * 状态占比
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryStateRate")
    public R queryStateRate(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", projectService.queryStateRate(params));
    }

    /**
     * 查询详细案件信息
     * @param parmas
     * @return
     */
    @ResponseBody
    @PostMapping("/queryProject")
    public R queryProject(@RequestParam Map<String, Object> parmas) {
        return R.ok().put("list", projectService.queryProject(parmas));
    }


    /**
     * 查询个人案件信息 By  employee
     * @param parmas
     * @return
     */
    @ResponseBody
    @PostMapping("/empPeoList")
    public R empPeoList(@RequestParam  Map<String, Object> parmas) {
        return R.ok().put("data", projectService.empPeoList(parmas));
    }
}
