package io.renren.modules.ansower_sheet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("ansower_sheet")
public class AnsowerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private int id ;
    //关联试卷表ID
    private int pid;
    //关联question表qid
    private int qid;
    //工号
    private int job_no;
    //员工答案
    private String ansower;
    //员工得分
    private int uscore;
    //开始时间
    private String start_time;
    //结束时间
    private String end_time;

    //创建时间
    private String create_time;

    public String getAnsower() {
        return ansower;
    }

    public void setAnsower(String ansower) {
        this.ansower = ansower;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getJob_no() {
        return job_no;
    }

    public void setJob_no(int job_no) {
        this.job_no = job_no;
    }

    public int getUscore() {
        return uscore;
    }

    public void setUscore(int uscore) {
        this.uscore = uscore;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
