package io.renren.modules.unexamemp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("unexamemp")
public class UnexampepolEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private  int id ;

    /**
     * 员工工号
     */
    private int job_no;
    /**
     * 试卷ID
     */
    private int pid;
    /**
     * 员工姓名
     */
    private  String name ;
    /**
     * 试卷标题
     */
    private  String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJob_no() {
        return job_no;
    }

    public void setJob_no(int job_no) {
        this.job_no = job_no;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
