package io.renren.modules.employees.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.employees.dao.EmployeesDao;
import io.renren.modules.employees.entity.EmployeesEntity;
import io.renren.modules.employees.service.EmployeesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("EmployeesService")
public class EmployeesServiceImpl extends ServiceImpl<EmployeesDao, EmployeesEntity> implements EmployeesService {
    /**
     * 查询员工信息
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> searchList(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("entryDateStart", dTime.split(",")[0]);
            params.put("entryDateEnd", dTime.split(",")[1]);
        }
        int total = baseMapper.searchListCount(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        params.put("rows", rows);
        params.put("page", page);
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.searchList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }

    /**
     * 导入员工信息
     * @param params
     * @return
     */
    @Override
    public Boolean saveEmployee(List<Map<String, Object>> params) {
        return baseMapper.saveEmployees(params);
    }


    /**
     * 查询所有下属员工ID
     * @param params
     * @return
     */
    @Override
    public String getPermission(Map<String, Object> params) {
        return null;
    }

    /**
     * 查询所有下属员工详细信息
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> getPermiEmpl(Map<String, Object> params) {
        return null;
    }


    /**
     * 获取员工个人信息
     * @param params
     * @return
     */
    @Override
    public Map getEmpByid(Map<String ,Object> params) {
        return baseMapper.getEmpByid(params);
    }


    /**
     * 员工离职
     * @param params
     * @return
     */
    @Override
    public Boolean setQuit(Map<String, Object> params) {
        return baseMapper.setQuit(params);
    }

    /**
     * 获取所有员工工号
     * @return
     */
    public List<Map<String,Object>> getJobNo() {
        return baseMapper.getJobNo();
    }

    /**
     * 刷新上下级关系
     * @return
     */
    @Override
    public Boolean updateDirector() {
        return baseMapper.updateDirector();
    }

    /**
     * 获取下属工号,ID
     * @return
     */
    public List<Map<String,Object>> getWorkEmpl(Map params) {
        return baseMapper.getWorkEmpl(params);
    }
    /**
     * 更新邮箱
     * @param params
     * @return
     */
    public Boolean upEmail(List<Map> params) {
        return baseMapper.upEmail(params);
    }
    /**
     * 标记异常人员
     * @return
     */
    @Override
    public Boolean exception() {
        return baseMapper.exception();
    }
}
