package io.renren.modules.position.controller;

import io.renren.common.utils.R;
import io.renren.modules.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 查询职称信息
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList() {
        List<Map> data =  positionService.search();
        return R.ok().put("data",data);
    }
    /**
     * 添加新的职称信息
     * @return
     */
    @ResponseBody
    @PostMapping("/insert")
    public R insertPosition(Map<String,Object> params) {
        positionService.save(params);
        return R.ok();
    }
    /**
     * 删除职称信息
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public R deletePosition(Map<String,Object> params) {
        positionService.delete(params);
        return R.ok();
    }
}
