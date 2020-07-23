package io.renren.modules.employees.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.employees.entity.EmployeesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeesDao extends BaseMapper<EmployeesEntity> {
    /**
     * 查询员工信息
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询员工信息<总条数>
     * @param params
     * @return
     */
    public int searchListCount(Map<String,Object> params);

    /**
     * 导入员工信息
     * @param params
     * @return
     */
    public Boolean  saveEmployees(List<Map<String,Object>> params);

    /**
     * 查询所有下属员工信息
     * @param params
     * @return
     */
    public List<Map<String, Object>> getPermiEmpl(Map<String, Object> params);

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
     * 刷新上下级关系
     * @return
     */
    public Boolean updateDirector();

    /**
     * 获取所有员工工号,ID
     * @return
     */
    public List<Map<String,Object>> getJobNo();
    /**
     * 获取下属工号,ID
     * @return
     */
    public List<Map<String,Object>> getWorkEmpl(Map params);

    /**
     * 更新邮箱
     * @param params
     * @return
     */
    public Boolean upEmail(List<Map> params);

    /**
     * 标记异常人员
     * @return
     */
    public Boolean exception();
}
