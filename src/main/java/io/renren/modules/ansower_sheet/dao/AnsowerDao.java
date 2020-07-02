package io.renren.modules.ansower_sheet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.ansower_sheet.entity.AnsowerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnsowerDao extends BaseMapper<AnsowerEntity> {
    /**
     * 查询答题信息
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询答题信息
     * @param params
     * @return
     */
    public int searchCount(Map<String,Object> params);

    /**
     * 导入员工答题数据
     * @param params
     * @return
     */
    public Boolean  saveAnsowers(List<Map<String,Object>> params);

    /**
     *查询主管下参考人员信息
     * @param params
     * @return
     */
    public List<Map> searchListByDirector(Map<String,Object> params);
    /**
     *查询主管下参考人员数目
     * @param params
     * @return
     */
    public int searchCountByDirector(Map<String,Object> params);

    /**
     * 合格人数
     * @param params
     * @return
     */
    public int queryQualified(Map<String,Object> params);

    /**
     * 获取试卷所有员工答题数据
     * @param params
     * @return
     */
    public List<Map<String,Object>> getAllExam(Map<String,Object> params);

    /**
     * 获取各主管下员工数据《统计》
     * @param params
     * @return
     */
    public Map examDataByDirector(Map<String, Object> params);
    /**
     * 获取各主管下员工数据《统计》
     * @param params
     * @return
     */
    public Map examHisData(Map<String, Object> params);

    /**
     * 临时表
     * @param params
     * @return
     */
    public Boolean createTemp(Map<String, Object> params);
    public Boolean cleaTemp();
}
