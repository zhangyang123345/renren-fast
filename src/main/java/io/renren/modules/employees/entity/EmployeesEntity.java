package io.renren.modules.employees.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("employee")
public class EmployeesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id ;
    /**
     * 工号-必填
     */
    private int job_no ;
    /**
     * 上级领导工号
     */
  //  private int pj_no;
    /**
     * 上级领导姓名
     */
    private String pj_name;
    /**
     * 人员类别
     */
    private String p_category;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 厂别
     */
    private String plant_type;
    /**
     * 部门
     */
    private String department;
    /**
     * 成本类别
     */
    private String cost_category;
    /**
     * 班级别
     */
    private String wclass;
    /**
     * 线级别
     */
    private String line_type;
    /**
     * 办公地点
     */
    private String office_location;
    /**
     * 主管
     */
    private String director;
    /**
     * 入职日期
     */
    private String entry_date;
    /**
     * 备注
     */
    private String comment;
    /**
     * 是否活动
     */
    private String active;

    /**
     * 职称
     */
    private String position;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

//    public int getPj_no() {
//        return pj_no;
//    }
//
//    public void setPj_no(int pj_no) {
//        this.pj_no = pj_no;
//    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlant_type() {
        return plant_type;
    }

    public void setPlant_type(String plant_type) {
        this.plant_type = plant_type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCost_category() {
        return cost_category;
    }

    public void setCost_category(String cost_category) {
        this.cost_category = cost_category;
    }

    public String getWclass() {
        return wclass;
    }

    public void setWclass(String wclass) {
        this.wclass = wclass;
    }

    public String getLine_type() {
        return line_type;
    }

    public void setLine_type(String line_type) {
        this.line_type = line_type;
    }

    public String getOffice_location() {
        return office_location;
    }

    public void setOffice_location(String office_location) {
        this.office_location = office_location;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPj_name() {
        return pj_name;
    }

    public void setPj_name(String pj_name) {
        this.pj_name = pj_name;
    }
}
