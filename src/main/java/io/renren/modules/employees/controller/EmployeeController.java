package io.renren.modules.employees.controller;

import io.renren.common.utils.JsonUtil;
import io.renren.common.utils.PoiUtils;
import io.renren.common.utils.R;
import io.renren.modules.employees.entity.EmployeesEntity;
import io.renren.modules.employees.service.EmployeesService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeesService employeesService;
    /**
     * 批量上传员工信息
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/import")
    public R uploadEmployee(MultipartFile file) {
        if (file == null) return R.error();
        JSONArray excelData = null;
        try {
            excelData = new PoiUtils().parseExcelFile(file,1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(excelData != null){
            List<Map<String, Object>> data = JsonUtil.jsonToObject(excelData.toString(), new ArrayList<Map<String, Object>>().getClass());
            List<Map<String,Object>> jobNo = employeesService.getJobNo();
            Map<String,Object> jobNoKey = new HashMap();
            List<EmployeesEntity> employeeList = new ArrayList<>();
            List<EmployeesEntity> updateList = new ArrayList<>();
            for (Map ma : jobNo) {
                jobNoKey.put(ma.get("jobNo").toString(), ma.get("id"));
            }
            for (Map<String, Object> dma : data) {
                if(dma.get("WD 工号")!=null){
                    EmployeesEntity employee = new EmployeesEntity();
                    employee.setActive("1");
                    employee.setJob_no(Integer.parseInt(dma.get("WD 工号").toString()));
                    if(dma.get("备注")!=null)employee.setComment(dma.get("备注").toString());
                    if(dma.get("姓名")!=null)employee.setName(dma.get("姓名").toString());
                    if(dma.get("性别")!=null)employee.setSex(dma.get("性别").toString());
                    if(dma.get("人员类别")!=null)employee.setP_category(dma.get("人员类别").toString());
                    if(dma.get("厂别")!=null)employee.setPlant_type(dma.get("厂别").toString());
                    if(dma.get("部门")!=null)employee.setDepartment(dma.get("部门").toString());
                    if(dma.get("职称")!=null)employee.setPosition(dma.get("职称").toString());
                    if(dma.get("成本别")!=null)employee.setCost_category(dma.get("成本别").toString());
                    if(dma.get("班别")!=null)employee.setWclass(dma.get("班别").toString());
                    if(dma.get("线别")!=null)employee.setLine_type(dma.get("线别").toString());
                    if(dma.get("办公楼栋")!=null)employee.setOffice_location(dma.get("办公楼栋").toString());
                    if(dma.get("主管")!=null)employee.setDirector(dma.get("主管").toString());
                    if(dma.get("入职日期")!=null)employee.setEntry_date(dma.get("入职日期").toString());
                  //  if(dma.get("上级工号")!=null)employee.setPj_no(Integer.parseInt(dma.get("上级工号").toString()));
                    if(dma.get("上级")!=null)employee.setPj_name(dma.get("上级").toString());
                    if(dma.get("邮箱")!=null)employee.setEmail(dma.get("邮箱").toString());
                    if(dma.get("联系方式")!=null)employee.setPhone(dma.get("联系方式").toString().indexOf(".")>0?dma.get("联系方式").toString().split("\\.")[0]:dma.get("联系方式").toString());
                    if(jobNoKey.containsKey(dma.get("WD 工号").toString())){
                        employee.setId(Integer.parseInt(jobNoKey.get(dma.get("WD 工号").toString()).toString()));
                        updateList.add(employee);
                    }else {
                        employeeList.add(employee);
                    }
                }
            }
            //批量保存
            if(employeeList.size()>0)employeesService.saveBatch(employeeList,500);
            if(updateList.size()>0) employeesService.updateBatchById(updateList,500);
            //刷新上下级关系
            employeesService.updateDirector();
            return R.ok();
        }
        return R.error("导入错误！");
    }

    /**
     * 批量更改离职员工信息
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadQuitEmpl")
    public R uploadQuitEmpl(MultipartFile file) {
        if (file == null) return R.error();
        JSONArray excelData = null;
        try {
            excelData = new PoiUtils().parseExcelFile(file,1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(excelData != null){
            List<Map<String, Object>> data = JsonUtil.jsonToObject(excelData.toString(), new ArrayList<Map<String, Object>>().getClass());
            List<Map<String,Object>> jobNo = employeesService.getJobNo();
            Map<String,Object> jobNoKey = new HashMap();
            List<EmployeesEntity> employeeList = new ArrayList<>();
            List<EmployeesEntity> updateList = new ArrayList<>();
            for (Map ma : jobNo) {
                jobNoKey.put(ma.get("jobNo").toString(), ma.get("id"));
            }
            for (Map<String, Object> dma : data) {
                if(dma.get("WD 工号")!=null){
                    EmployeesEntity employee = new EmployeesEntity();
                    employee.setActive("0");
                    employee.setJob_no(Integer.parseInt(dma.get("WD 工号").toString()));
                    if(dma.get("备注")!=null)employee.setComment(dma.get("备注").toString());
                    if(dma.get("姓名")!=null)employee.setName(dma.get("姓名").toString());
                    if(dma.get("性别")!=null)employee.setSex(dma.get("性别").toString());
                    if(dma.get("人员类别")!=null)employee.setP_category(dma.get("人员类别").toString());
                    if(dma.get("厂别")!=null)employee.setPlant_type(dma.get("厂别").toString());
                    if(dma.get("部门")!=null)employee.setDepartment(dma.get("部门").toString());
                    if(dma.get("职称")!=null)employee.setPosition(dma.get("职称").toString());
                    if(dma.get("成本别")!=null)employee.setCost_category(dma.get("成本别").toString());
                    if(dma.get("班别")!=null)employee.setWclass(dma.get("班别").toString());
                    if(dma.get("线别")!=null)employee.setLine_type(dma.get("线别").toString());
                    if(dma.get("办公楼栋")!=null)employee.setOffice_location(dma.get("办公楼栋").toString());
                    if(dma.get("主管")!=null)employee.setDirector(dma.get("主管").toString());
                    if(dma.get("入职日期")!=null)employee.setEntry_date(dma.get("入职日期").toString());
                    //  if(dma.get("上级工号")!=null)employee.setPj_no(Integer.parseInt(dma.get("上级工号").toString()));
                    if(dma.get("上级")!=null)employee.setPj_name(dma.get("上级").toString());
                    if(dma.get("邮箱")!=null)employee.setEmail(dma.get("邮箱").toString());
                    if(dma.get("联系方式")!=null)employee.setPhone(dma.get("联系方式").toString());
                    if(jobNoKey.containsKey(dma.get("WD 工号").toString())){
                        employee.setId(Integer.parseInt(jobNoKey.get(dma.get("WD 工号").toString()).toString()));
                        updateList.add(employee);
                    }else {
                        employeeList.add(employee);
                    }
                }
            }
            //批量保存
            if(employeeList.size()>0)employeesService.saveBatch(employeeList,500);
            if(updateList.size()>0) employeesService.updateBatchById(updateList,500);
            return R.ok();
        }
        return R.error("导入错误！");
    }


    /**
     * 查询员工信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList(@RequestParam Map<String,Object> parma) {
        Map<String,Object> data =  employeesService.searchList(parma);

        return R.ok().put("data",data);
    }
    /**
     * 保存更新员工信息
     * @return
     */
    @ResponseBody
    @PostMapping("/update")
    public R update(@RequestBody EmployeesEntity parma) {
        employeesService.updateById(parma);
        return R.ok();
    }

    /**
     * 新增员工信息
     * @return
     */
    @ResponseBody
    @PostMapping("/insert")
    public R insert(@RequestBody EmployeesEntity parma) {
        Map parm = new HashMap();
        parm.put("jobNo", parma.getJob_no());
        Map empl = employeesService.getEmpByid(parm);
        if(empl == null) {
            employeesService.save(parma);
            return R.ok();
        }else{
            return R.error("工号重复或保存错误！");
        }
    }

    /**
     * 获取员工个人信息
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/getEmp")
    public R save(@RequestParam Map<String ,Object> params) {
        Map data = employeesService.getEmpByid(params);
        return R.ok().put("data", data);
    }

    /**
     * 员工离职
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/setQuit")
    public R setQuit(@RequestBody  Map<String, Object> params) {
        employeesService.setQuit(params);
        return R.ok();
    }
}
