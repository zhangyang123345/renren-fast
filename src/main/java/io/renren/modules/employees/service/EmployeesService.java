package io.renren.modules.employees.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.employees.entity.EmployeesEntity;

import java.util.List;
import java.util.Map;

public interface EmployeesService  extends IService<EmployeesEntity> {
    /**
     * 查询员工信息
     * @param params
     * @return
     */
    public Map<String,Object> searchList(Map<String,Object> params);

    /**
     * 导入员工信息
     * @param params
     * @return
     */
    public Boolean  saveEmployee(List<Map<String,Object>> params);


    /**
     * 根据人员信息或当期账号信息查询下属员工ID
     * @param params
     * @return 返回所有权限范围内员工ID字符串
     */
    public String  getPermission(Map<String,Object> params);
    /**
     * 根据人员信息或当期账号信息查询下属员工ID
     * @param params
     * @return 返回所有权限范围内员工详细信息
     */
    public List<Map<String,Object>>  getPermiEmpl(Map<String,Object> params);

    /**
     * 获取员工个人信息
     * @param params
     * @return
     */
    public Map getEmpByid(Map<String ,Object> params);

    /**
     * 员工离职
     * @param params
     * @return
     */
    public Boolean setQuit(Map<String, Object> params);

    /**
     * 获取所有员工工号
     * @return
     */
    public List<Map<String,Object>> getJobNo();

    /**
     * 刷新上下级关系
     * @return
     */
    public Boolean updateDirector();

    /**
     * 获取下属工号,ID
     * @return
     */
    public List<Map<String,Object>> getWorkEmpl(Map params);

}
