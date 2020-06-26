package io.renren.modules.examination_paper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("examination_paper")
public class ExampaperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试卷ID
     */
    private int pid;
    /**
     * 试卷标题/描述
     */
    private String title;
    /**
     * 试卷类型
     */
    private String ptype;
    /**
     * 总分
     */
    private int sscore;
    /**
     * 合格分
     */
    private int pscore;
    /**
     * 考试时长
     */
    private int stime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public int getSscore() {
        return sscore;
    }

    public void setSscore(int sscore) {
        this.sscore = sscore;
    }

    public int getPscore() {
        return pscore;
    }

    public void setPscore(int pscore) {
        this.pscore = pscore;
    }

    public int getStime() {
        return stime;
    }

    public void setStime(int time) {
        this.stime = time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}


