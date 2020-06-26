package io.renren.modules.ansower_sheet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.ansower_sheet.dao.AnsowerDao;
import io.renren.modules.ansower_sheet.entity.AnsowerEntity;
import io.renren.modules.ansower_sheet.service.AnsowerService;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AnsowerService")
public class AnsowerServiceImpl extends ServiceImpl<AnsowerDao, AnsowerEntity> implements AnsowerService {


    @Autowired
    private UnexampepolService  unexampepolService;
    /**
     * 查询答题信息
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> searchList(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        int total = baseMapper.searchCount(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        if(params.get("rows")!=null) params.put("rows", Integer.parseInt(params.get("rows").toString()));
        if(params.get("page")!=null) params.put("page", Integer.parseInt(params.get("page").toString()));
        if(params.get("rows")==null) params.put("rows", 10);
        if(params.get("page")==null) params.put("page", 1);
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("sdate", dTime.split(",")[0]);
            params.put("edate", dTime.split(",")[1]);
        }
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.searchList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }

    /**
     * 导入员工答题数据
     * @param params
     * @return
     */
    @Override
    public Boolean saveAnsowers(List<Map<String, Object>> params) {
        return baseMapper.saveAnsowers(params);
    }

    /**
     * 查询主管下参考人员信息
     * @param params
     * @return
     */
    @Override
    public List<Map> searchListByDirector(Map<String, Object> params) {
//        Map<String, Object> data = new HashMap<>();
//        int total = baseMapper.searchCount(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        if(params.get("rows")!=null) params.put("rows", Integer.parseInt(params.get("rows").toString()));
        if(params.get("page")!=null) params.put("page", Integer.parseInt(params.get("page").toString()));
        if(params.get("rows")==null) params.put("rows", 10);
        if(params.get("page")==null) params.put("page", 1);
        params.put("pageOffset",pageOffset);
//        data.put("list",baseMapper.searchList(params));
//        data.put("total", total);
//        data.put("rows", rows);
//        data.put("page", page);
//        data.put("totalPage",Math.ceil(total/rows));
//        return data;
        return baseMapper.searchListByDirector(params);
    }

    /**
     *查询主管下参考人员数目
     * @param params
     * @return
     */
    public int searchCountByDirector(Map<String,Object> params) {
        return baseMapper.searchCountByDirector(params);
    }

    /**
     * 合格人数
     * @param params
     * @return
     */
    public int queryQualified(Map<String,Object> params) {
        return baseMapper.queryQualified(params);
    }

    /**
     * 获取各主管下员工数据《统计》
     * @param params
     * @return
     */
    @Override
    public Map examDataByDirector( Map<String,Object> params) {
        Map<String, Object> data = new HashMap<>();
        int unExamCount = unexampepolService.searchCountByDirector(params);//未参考人数
        int examCount   = baseMapper.searchCountByDirector(params); //参考人数
        int qualified = baseMapper.queryQualified(params); //合格人数
        int total = unExamCount + examCount;  //总人数
        data.put("unExamCount", unExamCount);
        data.put("examCount", examCount);
        data.put("qualified", qualified);
        data.put("total", total);
        return data;
    }

    /**
     * 获取试卷所有员工答题数据
     * @param params
     * @return
     */
    public List<Map<String,Object>> getAllExam(Map<String,Object> params) {
        return baseMapper.getAllExam(params);
    }
}
