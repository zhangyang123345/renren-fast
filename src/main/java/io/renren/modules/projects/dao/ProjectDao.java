package io.renren.modules.projects.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.projects.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {

    /**
     * 查询人员案件数据
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询人员案件数据<总数>
     * @param params
     * @return
     */
    public int searchListCount(Map<String,Object> params);

    /**
     * 查询案件数据
     * @param params
     * @return
     */
    public List<Map> projectList(Map<String,Object> params);
    /**
     * 查询案件数据<总数>
     * @param params
     * @return
     */
    public int projectListCount(Map<String,Object> params);

    /**
     * 获取案件ID以查重
     * @return
     */
    public List<Map> getPj_id ();

    /**
     * 更新目标-达标信息
     */
    public boolean updateTarget();

    /**
     * 获取阶段完成数量及目标 BY  director
     * @param params
     * @return
     */
    public Map queryLCount(Map<String, Object> params);

    /**
     * 查询Top10 人员
     * @param params
     * @return
     */
    public List<Map> queryTop(Map<String, Object> params);

    /**
     * 查询案件数量及类型比例
     * @param params
     * @return
     */
    public List<Map> queryTypeRate(Map<String, Object> params);
    /**
     * 状态占比
     * @param params
     * @return
     */
    public List<Map> queryStateRate(Map<String, Object> params);

    /**
     * 查询详细案件信息
     * @param parmas
     * @return
     */
    public List<Map> queryProject(Map<String, Object> parmas);
    /**
     * 查询个人案件信息 By  employee
     * @param parmas
     * @return
     */
    public List<Map> empPeoList(Map<String, Object> parmas);

    /**
     * 个人案件总数
     * @param parmas
     * @return
     */
    public int empPeoCount(Map<String, Object> parmas);
}
