package io.renren.modules.projects.controller;


import io.renren.common.utils.JsonUtil;
import io.renren.common.utils.PoiUtils;
import io.renren.common.utils.R;
import io.renren.modules.projects.entity.GlobalEntity;
import io.renren.modules.projects.service.GlobalService;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/global")
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    /**
     * 导入数据
     * @param file
     * @return
     */
    @ResponseBody
    @Transactional
    @PostMapping("/import")
    public R upload(MultipartFile file) {
        if (file == null) return R.error("文件异常！");
        try {
            JSONArray fileData = new PoiUtils().parseExcelFile(file, 1, 1,true,8,"Chengdu");
            List<Map> ansData = JsonUtil.jsonToObject(fileData.toString(), new ArrayList<Map>().getClass());
            //获取原数据
            List<Map> cellDate = globalService.getKey();
            Map<String, Object> cellKey = new HashMap();
            List<GlobalEntity> inserList = new ArrayList();
            List<GlobalEntity> updateList = new ArrayList();
            for (Map mp: cellDate) {
                cellKey.put(mp.get("job_no").toString(), mp.get("id"));
            }
            Pattern compile = Pattern.compile("[^0-9]");
            for (Map mp : ansData) {
                if ("Chengdu GP".equals(mp.get("Office").toString())) {
                    GlobalEntity global = new GlobalEntity();
                    String num = mp.get("Employee ID")!= null? compile.matcher(mp.get("Employee ID").toString().indexOf(".")>0?mp.get("Employee ID").toString().split("\\.")[0]:"").replaceAll(""):"";
                    if(num != null && StringUtils.isNotEmpty(num))global.setJob_no(Integer.valueOf(num));
                    global.setEmail(mp.get("Email") != null ? mp.get("Email").toString() : "");
                    global.setRegion(mp.get("Region") != null ? mp.get("Region").toString() : "");
                    global.setPass_exam(mp.get("Passed Exam") != null && "true".equals(mp.get("Passed Exam").toString()) ? 1 : 0);
                    global.setCertification_no(mp.get("Certification Number") != null ? mp.get("Certification Number").toString() : "");
                    global.setCertification_date(mp.get("Certification Date") != null ? mp.get("Certification Date").toString() : "");
                    global.setExpire_on(mp.get("Expire On") != null ? mp.get("Expire On").toString() : "");
                    global.setValid(mp.get("Valid") != null && "Yes".equals(mp.get("Valid").toString()) ? 1 : 0);
                    global.setBlitz(mp.get("Blitz") != null ? mp.get("Blitz").toString() : "");
                    global.setPja_1(mp.get("A1") != null ? mp.get("A1").toString() : "");
                    global.setPja_2(mp.get("A2") != null ? mp.get("A2").toString() : "");
                    global.setPja_3(mp.get("A3") != null ? mp.get("A3").toString() : "");
                    global.setPja_4(mp.get("A4") != null ? mp.get("A4").toString() : "");

                    if (cellKey.containsKey(num)) {
                        global.setId(Integer.parseInt(cellKey.get(num).toString()));
                        updateList.add(global);
                    } else {
                        inserList.add(global);
                    }
                }
            }
            if(inserList.size()>0) globalService.saveBatch(inserList, 1000);
            if(updateList.size()>0) globalService.updateBatchById(updateList, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("导入成功");
    }

    /**
     * 查询列表
     */
    @ResponseBody
    @PostMapping("/search")
    public R search(@RequestParam  Map<String, Object> params) {
        return R.ok().put("data", globalService.search(params));
    }

    /**
     * 完成率图表
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/searchTable")
    public R searchTable(@RequestParam  Map<String, Object> params) {
        return R.ok().put("data", globalService.searchTable(params));
    }

    /**
     * 导出报表
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/listAll")
    public R listAll(@RequestParam  Map<String, Object> params) {
        return R.ok().put("list", globalService.listAll(params));
    }
}