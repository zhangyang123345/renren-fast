package io.renren.modules.projects.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.projects.dao.GlobalDao;
import io.renren.modules.projects.entity.GlobalEntity;
import io.renren.modules.projects.service.GlobalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("GlobalService")
public class GlobalServiceImpl extends ServiceImpl<GlobalDao, GlobalEntity> implements GlobalService {
    /**
     * 获取Key 值，去重处理
     * @return
     */
    @Override
    public List<Map> getKey() {
        return baseMapper.getKey();
    }

    /**
     * 查询列表
     */
    @Override
    public Map search(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<>();
        String dTime = params.get("keytime")!=null?params.get("keytime").toString():"";
        int rows = params.get("rows") != null ? Integer.parseInt(params.get("rows").toString()): 10;
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int pageOffset = rows*(page - 1);
        params.put("rows", rows);
        params.put("page", page);
        if(StringUtils.isNotBlank(dTime) && !"null".equals(dTime)){
            params.put("start", dTime.split(",")[0]);
            params.put("end", dTime.split(",")[1]);
        }
        int total = baseMapper.searchCount(params);
        params.put("pageOffset",pageOffset);
        data.put("list",baseMapper.search(params));
        data.put("total", total);
        data.put("rows", rows);
        data.put("page", page);
        data.put("totalPage",Math.ceil(total/rows));
        return data;
    }

    /**
     * 完成率图表
     * @param params
     * @return
     */
    @Override
    public List<Map> searchTable(Map<String, Object> params) {

        List<Map> data =  baseMapper.searchTable(params);
        if(data != null) {
            Map xiao = new HashMap();
            xiao.put("name", "PVD厂");
            xiao.put("jobNo", 451692);
            int target = 0;
            int count = 0;
            for (Map mp : data) {
                target += (mp.get("target") != null ? Integer.parseInt(mp.get("target").toString()) : 0);
                count += (mp.get("count") != null ? Integer.parseInt(mp.get("count").toString()) : 0);
            }
            xiao.put("target", target);
            xiao.put("count", count);
            xiao.put("rate", count/target);
            data.add(0, xiao);
        }
        return data;
    }

    /**
     * 导出报表
     * @param params
     * @return
     */
    @Override
    public List<Map> listAll(Map<String, Object> params) {
        return baseMapper.listAll(params);
    }
}
