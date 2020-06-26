package io.renren.modules.projects.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("projects")
public class ProjectDetailEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private  int  id ;
    /**
     * 案件ID
     */
    private  int project_id;
    /**
     *参与者
     */
    private String team_member;
    /**
     *参与者邮箱
     */
    private String member_email;

    /**
     *案件类型
     */
    private  String project_type;
    /**
     * 提交时间
     */
    private  String submit_date;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTeam_member() {
        return team_member;
    }

    public void setTeam_member(String team_member) {
        this.team_member = team_member;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }
}
