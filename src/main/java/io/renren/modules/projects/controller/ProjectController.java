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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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
     * 查询人员案件数据及导出<ALL>
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/listAll")
    public R listAll(@RequestParam  Map<String,Object> params) {
        List<Map> list = projectService.listAll(params);
//        //排序
//        Double[] soruce = new Double[list.size()];
//        int[] index = new int[list.size()];
//        int i = 0 ;
//        for (Map mp : list) {
//            Double  rate = mp.get("rate")!=null?Double.valueOf(mp.get("rate").toString()):0.0;
//            soruce[i] = rate;
//            index[i] = i++;
//        }
//        SortUtils.quickSort(soruce,index,0,soruce.length-1);
//        List<Map> data = new ArrayList<>();
//        for (int ind : index) {
//            data.add(list.get(ind));
//        }
     return  R.ok().put("list",list);
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
            JSONArray fileData = new PoiUtils().parseExcelFile(file, 1, 1,true ,14,"Chengdu");
            List<Map> ansData = JsonUtil.jsonToObject(fileData.toString(), new ArrayList<Map>().getClass());

            //获取原数据
            List<Map> cellDate = projectService.getPj_id();
            Map<String, Object> cellKey = new HashMap();
            for (Map mp : cellDate) {
                cellKey.put(mp.get("projectId").toString(), (mp.get("id").toString()+"-"+mp.get("closeDate")));
            }

            //筛选时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -9);
            Date ddte = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

            List<ProjectEntity> inserList = new ArrayList();
            List<ProjectEntity> updateList = new ArrayList();
            List<ProjectDetailEntity> insDList = new ArrayList();
            List<Map> updatDList = new ArrayList();
            Map<String, Map> target = new HashMap<>();
            //处理导入数据
            Pattern compile = Pattern.compile("[^0-9]");
            for (Map mp : ansData) {
                String submitDate = mp.get("Submitted Date") != null ? mp.get("Submitted Date").toString() : "";
                if (StringUtils.isNotEmpty(submitDate) && ddte.before(format.parse(submitDate))) {
                    ProjectEntity project = new ProjectEntity();
                    project.setSubmit_date(submitDate);
                    int projectId = mp.get("Project Id") != null ? Double.valueOf(mp.get("Project Id").toString()).intValue() : null;
                    project.setProject_id(projectId);
                    project.setProject_name(mp.get("Project Name") != null ? mp.get("Project Name").toString() : "");
                    project.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                    project.setState(mp.get("State") != null ? mp.get("State").toString() : "");
                    project.setLsc_date(mp.get("Date of Last State Change") != null ? mp.get("Date of Last State Change").toString() : "");
                    project.setSubmit_email(mp.get("Submitted By Email Address") != null ? mp.get("Submitted By Email Address").toString() : "");
                    project.setProcess_improve(mp.get("Process Improved") != null ? mp.get("Process Improved").toString() : "");
                    project.setProcess_email(mp.get("Approver Email Address") != null ? mp.get("Approver Email Address").toString() : "");
                    project.setTeam_leader(mp.get("Team Leader") != null ? mp.get("Team Leader").toString() : "");
                    project.setLeader_email(mp.get("Team Leader Email") != null ? mp.get("Team Leader Email").toString() : "");
                    project.setCustomer_field(mp.get("Custom Field 1") != null ? mp.get("Custom Field 1").toString() : "");
                    project.setImpact_on_PL(mp.get("Impact on P&L").toString());
                    if (mp.get("Estimated Hard Savings") != null)
                        project.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()));
                    if (mp.get("Closed Date") != null) project.setClose_date(mp.get("Closed Date").toString());
                    String nids = "";
                    if (mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString())) {
                        String nid = compile.matcher(mp.get("Non NT ID Team Member 1").toString().split("\\.")[0]).replaceAll("");
                        if (nid.length() > 10) nid = "";
                        if (!"".equals(nid)) nids += nid;
                    }
                    if (mp.get("Non NT ID Team Member 2") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 2").toString())) {
                        String nid = compile.matcher(mp.get("Non NT ID Team Member 2").toString().split("\\.")[0]).replaceAll("");
                        if (nid.length() > 10) nid = "";
                        if (!"".equals(nid)) nids += "," + nid;
                    }
                    if (mp.get("Non NT ID Team Member 3") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 3").toString())) {
                        String nid = compile.matcher(mp.get("Non NT ID Team Member 3").toString().split("\\.")[0]).replaceAll("");
                        if (nid.length() > 10) nid = "";
                        if (nid != "") nids += "," + nid;
                    }
                    if (!"".equals(nids)) project.setNntid(nids);
                    String teamMember = "";
                    String memberEmail = "";
                    int membIndex = 1;
                    while (mp.get("Team Member " + membIndex) != null && StringUtils.isNotEmpty(mp.get("Team Member " + membIndex).toString())) {
                        teamMember += mp.get("Team Member " + membIndex).toString() + ",";
                        memberEmail += mp.get("Team Member " + membIndex++ + " Email Address").toString() + ",";
                    }
                    if (teamMember.length() > 1) teamMember = teamMember.substring(0, teamMember.length() - 1);
                    if (memberEmail.length() > 1) memberEmail = memberEmail.substring(0, memberEmail.length() - 1);

                    project.setTeam_member(teamMember);
                    project.setMember_email(memberEmail);
                    if (cellKey.containsKey(String.valueOf(projectId))) {
                        if("null".equals(cellKey.get(String.valueOf(projectId)).toString().split("-")[1])) {
                            project.setId(Integer.parseInt(cellKey.get(String.valueOf(projectId)).toString().split("-")[0]));
                            updateList.add(project);
                            if (mp.get("Closed Date") != null && StringUtils.isNotEmpty(mp.get("Closed Date").toString())) {
                                Map detail = new HashMap();
                                detail.put("projectId", mp.get("Project Id").toString());
                                detail.put("closeDate", mp.get("Closed Date").toString());
                                updatDList.add(detail);
                            }
                        }
                    } else {
                        if ("Blitz".equals(mp.get("Project Type").toString())) {
                            if (mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 1").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid)) {
                                    ProjectDetailEntity detailEntity = new ProjectDetailEntity();
                                    detailEntity.setNntid(Integer.parseInt(nid));
                                    detailEntity.setProject_id(projectId);
                                    detailEntity.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    detailEntity.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                    if (mp.get("Estimated Hard Savings") != null && StringUtils.isNotEmpty(mp.get("Estimated Hard Savings").toString()))
                                        detailEntity.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()));
                                    insDList.add(detailEntity);
                                }
                            }
                        } else if ("A3".equals(mp.get("Project Type").toString())) {
                            ProjectDetailEntity member1 = null;
                            ProjectDetailEntity member2 = null;
                            ProjectDetailEntity member3 = null;
                            int numb = 0;
                            String nidb = "";

                            if (mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 1").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid)) {
                                    nidb += nid;
                                    numb++;
                                    member1 = new ProjectDetailEntity();
                                    member1.setNntid(Integer.parseInt(nid));
                                    member1.setProject_id(projectId);
                                    member1.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member1.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }
                            if (mp.get("Non NT ID Team Member 2") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 2").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 2").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid) && nidb.indexOf(nid) < 0) {
                                    nidb += "," + nid;
                                    numb++;
                                    member2 = new ProjectDetailEntity();
                                    member2.setNntid(Integer.parseInt(nid));
                                    member2.setProject_id(projectId);
                                    member2.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member2.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }
                            if (mp.get("Non NT ID Team Member 3") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 3").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 3").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid) && nidb.indexOf(nid) < 0) {
                                    nidb += "," + nid;
                                    numb++;
                                    member3 = new ProjectDetailEntity();
                                    member3.setNntid(Integer.parseInt(nid));
                                    member3.setProject_id(projectId);
                                    member3.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member3.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }

                            if (mp.get("Estimated Hard Savings") != null && StringUtils.isNotEmpty(mp.get("Estimated Hard Savings").toString())) {
                                if (numb == 1) {
                                    if (member1 != null)
                                        member1.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()));
                                    if (member2 != null)
                                        member2.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()));
                                    if (member3 != null)
                                        member3.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()));
                                } else if (numb == 2) {
                                    if (member1 != null)
                                        member1.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.5);
                                    if (member2 != null)
                                        member2.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.5);
                                    if (member3 != null)
                                        member3.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.5);
                                } else {
                                    if (member1 != null)
                                        member1.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.4);
                                    if (member2 != null)
                                        member2.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.3);
                                    if (member3 != null)
                                        member3.setHard_saving(Double.valueOf(mp.get("Estimated Hard Savings").toString()) * 0.3);
                                }
                            }
                            if (member1 != null) insDList.add(member1);
                            if (member2 != null) insDList.add(member2);
                            if (member3 != null) insDList.add(member3);
                        } else {
                            ProjectDetailEntity leader = new ProjectDetailEntity();
                            List<ProjectDetailEntity> cache = new ArrayList<>();

                            ProjectDetailEntity member1 = null;
                            ProjectDetailEntity member2 = null;
                            ProjectDetailEntity member3 = null;
                            int numb = 2;
                            String nidt = "";

                            //Team leader
                            leader.setProject_id(projectId);
                            leader.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                            leader.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                            leader.setMember_email(mp.get("Team Leader Email").toString());

                            //NNTID
                            if (mp.get("Non NT ID Team Member 1") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 1").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 1").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid)) {
                                    nidt += nid;
                                    numb++;
                                    member1 = new ProjectDetailEntity();
                                    member1.setNntid(Integer.parseInt(nid));
                                    member1.setProject_id(projectId);
                                    member1.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member1.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }
                            if (mp.get("Non NT ID Team Member 2") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 2").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 2").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid) && nidt.indexOf(nid) < 0) {
                                    nidt += "," + nid;
                                    numb++;
                                    member2 = new ProjectDetailEntity();
                                    member2.setNntid(Integer.parseInt(nid));
                                    member2.setProject_id(projectId);
                                    member2.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member2.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }
                            if (mp.get("Non NT ID Team Member 3") != null && StringUtils.isNotBlank(mp.get("Non NT ID Team Member 3").toString())) {
                                String nid = compile.matcher(mp.get("Non NT ID Team Member 3").toString().split("\\.")[0]).replaceAll("");
                                if (nid.length() > 10) nid = "";
                                if (!"".equals(nid) && nidt.indexOf(nid) < 0) {
                                    nidt += "," + nid;
                                    numb++;
                                    member3 = new ProjectDetailEntity();
                                    member3.setNntid(Integer.parseInt(nid));
                                    member3.setProject_id(projectId);
                                    member3.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                    member3.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                }
                            }

                            int membrIndex = 1;
                            while (mp.get("Team Member " + membrIndex) != null && StringUtils.isNotEmpty(mp.get("Team Member " + membrIndex).toString())) {
                                ProjectDetailEntity member = new ProjectDetailEntity();
                                member.setProject_id(projectId);
                                member.setMember_email(mp.get("Team Member " + membrIndex + " Email Address") != null ? mp.get("Team Member " + membrIndex++ + " Email Address").toString() : "");
                                member.setProject_type(mp.get("Project Type") != null ? mp.get("Project Type").toString() : "");
                                member.setClose_date(mp.get("Closed Date") != null ? mp.get("Closed Date").toString() : "");
                                cache.add(member);
                                numb++;
                            }
                            if (mp.get("Estimated Hard Savings") != null && StringUtils.isNotEmpty(mp.get("Estimated Hard Savings").toString())) {
                                Double money = Double.valueOf(mp.get("Estimated Hard Savings").toString());
                                Double leadm = money % numb;
                                Double cham = money - leadm;
                                leader.setHard_saving((cham / numb) * 2 + leadm);
                                if (member1 != null) member1.setHard_saving(cham / numb);
                                if (member2 != null) member2.setHard_saving(cham / numb);
                                if (member3 != null) member3.setHard_saving(cham / numb);
                                for (ProjectDetailEntity pj : cache) {
                                    pj.setHard_saving(cham / numb);
                                }
                            }
                            insDList.add(leader);
                            if (member1 != null) insDList.add(member1);
                            if (member2 != null) insDList.add(member2);
                            if (member3 != null) insDList.add(member3);
                            for (ProjectDetailEntity pj : cache) {
                                insDList.add(pj);
                            }
                        }
                        inserList.add(project);
                    }
                }
        }
//            for (ProjectEntity mp : inserList) {
//                System.out.println(mp.toString());
//            }
//            更新数据
            if(inserList.size()>0) projectService.saveBatch(inserList, 2000);
            if(updateList.size()>0) projectService.updateBatchById(updateList, 500);
            if(insDList.size()>0) projectDetailService.saveBatch(insDList, 4500);
            if(updatDList.size()>0) projectDetailService.updateDetail(updatDList);
            //处理达标数据
            if(insDList.size()>0 || updatDList.size()>0)projectService.updateTarget();
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
    public R queryLTable(@RequestParam Map<String , Object> params) {
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("start", dTime.split(",")[0]);
            params.put("end", dTime.split(",")[1]);
        }
        List<Map> emp = projectService.queryLCount(params);
        Map<String, Object> data = new HashMap<>();
        DecimalFormat format = new DecimalFormat("0.00");
        List<String> name = new ArrayList();
        List<Integer> count = new ArrayList();
        List<Integer> target = new ArrayList();
        List<String> rate = new ArrayList();
        for (Map<String, Object> mp : emp) {
            int coun = mp.get("num") != null ? Integer.parseInt(mp.get("num").toString()):0;
            int tage = mp.get("target") != null ? Integer.parseInt(mp.get("target").toString()):0;
            count.add(coun);
            target.add(tage);
            Double rates = tage!=0?(Double.valueOf(coun)/Double.valueOf(tage))*100:0.00;
            name.add(mp.get("name").toString());
            rate.add(format.format(rates));
        }
        data.put("name", name.toArray());
        data.put("count", count.toArray());
        data.put("target", target.toArray());
        data.put("rate", rate.toArray());
        return R.ok().put("data", data);
    }

    /**
     * 主管金额占比
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/moneyRate")
    public R moneyRate(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", projectService.moneyRate(params));
    }

    /**
     * 节约类型占比
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/impactRate")
    public R impactRate(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", projectService.impactRate(params));
    }

    /**
     * 查询Top10 人员
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/queryTop")
    public R queryTop(@RequestParam Map<String ,Object> params) {
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

    /**
     * 获取厂别列表
     * @return
     */
    @ResponseBody
    @GetMapping("/getProcess")
    public R  getProcess() {
        return R.ok().put("list", projectService.getProcess());
    }

    /**
     * 导出案件信息
     * @return
     */
    @ResponseBody
    @PostMapping("/exportCaseMsg")
    public R exportCaseMsg(@RequestParam Map<String,Object> params) {
        return R.ok().put("data", projectService.exportCaseMsg(params));
    }

    /**
     * 手动更新目标
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/setTarget")
    public R setTarget() {
        projectService.setTarget();
        return R.ok();
    }
}
