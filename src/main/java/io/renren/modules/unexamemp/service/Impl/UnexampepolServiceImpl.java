package io.renren.modules.unexamemp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.unexamemp.dao.UnexampepolDao;
import io.renren.modules.unexamemp.entity.UnexampepolEntity;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UnexampepolService")
public class UnexampepolServiceImpl extends ServiceImpl<UnexampepolDao, UnexampepolEntity> implements UnexampepolService {
    /**
     * 查询未参考人员信息List
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> searchList(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        int total = baseMapper.searchListCount(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        if(params.get("rows")!=null) params.put("rows", Integer.parseInt(params.get("rows").toString()));
        if(params.get("page")!=null) params.put("page", Integer.parseInt(params.get("page").toString()));
        if(params.get("rows")==null) params.put("rows", 10);
        if(params.get("page")==null) params.put("page", 1);
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.searchList(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }

    /**
     * 批量导入
     * @param params
     * @return
     */
    @Override
    public Boolean saveUnexampepol(List<Map<String, Object>> params) {
        return baseMapper.saveUnexampepol(params);
    }

    /**
     * 更新未参考人员名单
     * @param params
     * @return
     */
    @Override
    public Boolean updateUnexam(ExampaperEntity params){
        return baseMapper.updateUnexam(params);
    }

    /**
     * 根据主管查询未参考数据信息List
     * @param params
     * @return
     */
    @Override
    public Map searchListByDirector(Map<String,Object> params) {
        Map<String, Object> data = new HashMap<>();
        int total = baseMapper.searchCountByDirector(params);
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()) : 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows * (page - 1);
        if (params.get("rows") != null) params.put("rows", Integer.parseInt(params.get("rows").toString()));
        if (params.get("page") != null) params.put("page", Integer.parseInt(params.get("page").toString()));
        if (params.get("rows") == null) params.put("rows", 10);
        if (params.get("page") == null) params.put("page", 1);
        params.put("pageOffset", pageOffset);
        data.put("list", baseMapper.searchListByDirector(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage", Math.ceil(total / rows));
        return data;
    }
    /**
     * 根据主管查询未参考数<总数>
     * @param params
     * @return
     */
    @Override
    public int searchCountByDirector(Map<String,Object> params){
        return baseMapper.searchCountByDirector(params);
    }
    /**
     * 导出未参考人员名单
     * @param params
     * @return
     */
    @Override
    public List<Map> exportExcel(Map<String, Object> params) {
        return baseMapper.exportExcel(params);
    }
}
