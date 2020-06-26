package io.renren.modules.unexamemp.controller;


import io.renren.common.utils.R;
import io.renren.modules.unexamemp.service.UnexampepolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unexam")
public class UnexampepolController {
    @Autowired
    private UnexampepolService unexampepolService;

    /**
     * 查询未参考人员名单信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchExm(@RequestParam Map<String,Object> parma) {
        Map<String,Object> data =  unexampepolService.searchList(parma);
        return R.ok().put("data",data);
    }

    /**
     * 根据主管查询未参考数据---List
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/searchListByDirector")
    public R searchListByDirector(@RequestParam  Map<String,Object> params) {
        Map data = unexampepolService.searchListByDirector(params);
        return R.ok().put("data", data);
    }

    /**
     * 根据主管查询未参考数<总数>
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/searchCountByDirector")
    public R searchCountByDirector(Map<String,Object> params){
        int count = unexampepolService.searchCountByDirector(params);
        return R.ok().put("count", count);
    }

    /**
     * 导出未参考人员名单
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/exportExcel")
    public R exportExcel(@RequestParam  Map<String, Object> params) {
        List<Map> data = unexampepolService.exportExcel(params);
        return R.ok().put("list", data);
    }
}
