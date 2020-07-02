package io.renren.modules.projects.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("projects")
public class ProjectEntity implements Serializable {
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
     *改善案主题名
     */
    private  String project_name;
    /**
     *改善案类型
     */
    private String project_type;
    /**
     *状态
     */
    private String state;
    /**
     *最后更新时间
     */
    private String lsc_date;
    /**
     *提交时间
     */
    private String submit_date;
    /**
     *提交邮箱
     */
    private String submit_email;
    /**
     *改善所属部门
     */
    private String process_improve;
    /**
     *审核主管邮箱
     */
    private String process_email;
    /**
     *项目领导者
     */
    private String team_leader;
    /**
     *leader邮箱
     */
    private String leader_email;
    /**
     *参与者（；分隔）
     */
    private String team_member;
    /**
     *参与者邮箱（；分隔）
     */
    private String member_email;
    /**
     *产品案别
     */
    private String customer_field;
    /**
     *完成时间
     */
    private Date close_date = null;

    /**
     * Non NT ID
     * @return
     */
    private  int nntid;

    public int getNntid() {
        return nntid;
    }

    public void setNntid(int nntid) {
        this.nntid = nntid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLsc_date() {
        return lsc_date;
    }

    public void setLsc_date(String lsc_date) {
        this.lsc_date = lsc_date;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getSubmit_email() {
        return submit_email;
    }

    public void setSubmit_email(String submit_email) {
        this.submit_email = submit_email;
    }

    public String getProcess_improve() {
        return process_improve;
    }

    public void setProcess_improve(String process_improve) {
        this.process_improve = process_improve;
    }

    public String getProcess_email() {
        return process_email;
    }

    public void setProcess_email(String process_email) {
        this.process_email = process_email;
    }

    public String getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(String team_leader) {
        this.team_leader = team_leader;
    }

    public String getLeader_email() {
        return leader_email;
    }

    public void setLeader_email(String leader_email) {
        this.leader_email = leader_email;
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

    public String getCustomer_field() {
        return customer_field;
    }

    public void setCustomer_field(String customer_field) {
        this.customer_field = customer_field;
    }

    public Date getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        if (close_date != null && StringUtils.isNotEmpty(close_date)) {
            if(close_date.indexOf(" ")>0) close_date = close_date.substring(0, close_date.indexOf(" "));
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(close_date);
                this.close_date = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

