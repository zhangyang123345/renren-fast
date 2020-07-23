package io.renren.modules.projects.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.projects.dao.GlobalDao;
import io.renren.modules.projects.entity.GlobalEntity;
import io.renren.modules.projects.service.GlobalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    public Map searchTable(Map<String, Object> params) {

        Map result = new HashMap();
        List<String> name = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<Integer> target = new ArrayList<>();
        List<String> rate = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("0.00");
        int ptarget = 0;
        int pcount = 0;
        List<Map> data =  baseMapper.searchTable(params);
        if(data != null) {
            for (Map mp : data) {
                int targetc = mp.get("target") != null ? Integer.parseInt(mp.get("target").toString()) : 0;
                int countc = mp.get("count") != null ? Integer.parseInt(mp.get("count").toString()) : 0;
                Double ratec = mp.get("rate") != null ?Double.valueOf(mp.get("rate").toString()):0.00;
                name.add(mp.get("name")!=null?mp.get("name").toString():"");
                count.add(countc);
                target.add(targetc);
                rate.add(format.format(ratec * 100));

                ptarget += targetc;
                pcount += countc;
            }
            name.add(0,"PVD厂");
            count.add(0,pcount);
            target.add(0,ptarget);
            rate.add(0, format.format(ptarget != 0 ? (Double.valueOf(pcount) / Double.valueOf(ptarget)) * 100 : 0.0));
        }
        result.put("name", name);
        result.put("rate", rate);
        result.put("count", count);
        result.put("target", target);
        return result;
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
