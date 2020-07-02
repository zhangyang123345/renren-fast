package io.renren.modules.ansower_sheet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.ansower_sheet.entity.AnsowerEntity;

import java.util.List;
import java.util.Map;

public interface AnsowerService extends IService<AnsowerEntity> {
    /**
     * 查询答题信息
     * @param params
     * @return
     */
    public Map<String,Object> searchList(Map<String,Object> params);

    /**
     * 导入员工答题数据
     * @param params
     * @return
     */
    public Boolean  saveAnsowers(List<Map<String,Object>> params);

    /**
     * 查询主管下参考人员信息
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
     * 获取各主管下员工数据《统计》
     * @param params
     * @return
     */
    public Map examDataByDirector( Map<String,Object> params) ;

    /**
     * 获取各主管下员工数据《统计》
     *
     * @param params
     * @return
     */
    public Map examHisData(Map<String, Object> params);
    /**
     * 获取试卷所有员工答题数据
     * @param params
     * @return
     */
    public List<Map<String,Object>> getAllExam(Map<String,Object> params);
}
