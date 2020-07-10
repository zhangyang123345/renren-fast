package io.renren.modules.projects.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("project_detail")
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
     *参与者邮箱
     */
    private String member_email;
    /**
     * 完成时间
     */
    private Date close_date;
    /**
     *案件类型
     */
    private  String project_type;
    /**
     * NTID
     */
    private int nntid;
    /**
     * 节约金额
     */
    private  Double  hard_saving ;

    private  static DecimalFormat format = new DecimalFormat("0.0000");

    public Double getHard_saving() {
        return hard_saving;
    }

    public void setHard_saving(Double hard_saving) {
        this.hard_saving = Double.valueOf(format.format(hard_saving));
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

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
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

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public int getNntid() {
        return nntid;
    }

    public void setNntid(int nntid) {
        this.nntid = nntid;
    }
}