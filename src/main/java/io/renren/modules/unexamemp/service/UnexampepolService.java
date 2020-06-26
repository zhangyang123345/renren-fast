package io.renren.modules.unexamemp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.unexamemp.entity.UnexampepolEntity;

import java.util.List;
import java.util.Map;

public interface UnexampepolService extends IService<UnexampepolEntity> {
    /**
     * 查询未参考人员信息 List
     *
     * @param params
     * @return
     */
    public Map<String, Object> searchList(Map<String, Object> params);

    /**
     * 批量导入
     *
     * @param params
     * @return
     */
    public Boolean saveUnexampepol(List<Map<String, Object>> params);

    /**
     * 更新未参考人员名单
     * @param params
     * @return
     */
    public Boolean updateUnexam(ExampaperEntity params);

    /**
     * 根据主管查询未参考数据<List>
     * @param params
     * @return
     */
    public Map searchListByDirector(Map<String,Object> params);

    /**
     * 根据主管查询未参考数<总数>
     * @param params
     * @return
     */
    public int searchCountByDirector(Map<String,Object> params);

    /**
     * 导出未参考人员名单
     * @param params
     * @return
     */
    public List<Map> exportExcel(Map<String, Object> params);
}