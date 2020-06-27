package io.renren.modules.unexamemp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.examination_paper.entity.ExampaperEntity;
import io.renren.modules.unexamemp.entity.UnexampepolEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UnexampepolDao extends BaseMapper<UnexampepolEntity> {
    /**
     * 查询List
     * @param params
     * @return
     */
    public List<Map> searchList(Map<String,Object> params);
    /**
     * 查询列表<总数>
     * @param params
     * @return
     */
    public int searchListCount(Map<String,Object> params);

    /**
     * 批量导入
     * @param params
     * @return
     */
    public Boolean  saveUnexampepol(List<Map<String,Object>> params);

    /**
     * 同步更新未参考人员名单
     * @param params
     * @return
     */
    public Boolean deleteUnexam(ExampaperEntity params);
    /**
     * 更新未参考人员名单
     * @param params
     * @return
     */
    public Boolean updateUnexam(ExampaperEntity params);

    /**
     * 根据主管查询未参考人员数据
     * @param params
     * @return
     */
    public List<Map> searchListByDirector(Map<String,Object> params);
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
