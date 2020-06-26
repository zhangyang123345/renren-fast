package io.renren.modules.lien_type.controller;


import io.renren.common.utils.R;
import io.renren.modules.lien_type.service.LineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lineType")
public class LineTypeController {

    @Autowired
    private LineTypeService lineTypeService;

    /**
     * 查询所有线别信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList() {
        List<Map> data =  lineTypeService.search();
        return R.ok().put("data",data);
    }
    /**
     * =插入线别信息
     * @return
     */
    @ResponseBody
    @PostMapping("/insert")
    public R insertPosition(Map<String,Object> params) {
        lineTypeService.save(params);
        return R.ok();
    }
    /**
     * 删除线别信息
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public R deletePosition(Map<String,Object> params) {
        lineTypeService.delete(params);
        return R.ok();
    }
}
