package io.renren.modules.projects.controller;

import io.renren.common.utils.R;
import io.renren.modules.projects.service.projectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/project")
public class projectController {

    @Autowired
    private projectService projectService;

    /**
     * 查询试题
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/search")
    public R searchList(@RequestParam Map<String,Object> params) {
        return R.ok().put("data", projectService.searchList(params));
    }

    /**
     * 查询试题
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/import")
    public R upload(MultipartFile file, @RequestParam Map<String,Object> params) {
        return R.ok().put("data", projectService.searchList(params));
    }
}
