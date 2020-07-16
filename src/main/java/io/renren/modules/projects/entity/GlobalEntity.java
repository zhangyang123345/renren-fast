package io.renren.modules.projects.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("global")
public class GlobalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private  int id ;

    /**
     * 工号
     */
    private int job_no;
    /**
     * 邮箱
     */
    private  String email;

    /**
     * 区域
     */
    private  String region ;
    /**
     * 是否通过考试
     */
    private  int pass_exam;
    /**
     * 证书编号
     */
    private String  certification_no;
    /**
     * 生效日期
     */
    private Date certification_date;
    /**
     * 有效日期
     */
    private Date expire_on;
    /**
     * 是否有效
     */
    private  int valid;
    /**
     * blitz  状态
     */
    private String blitz;
    /**
     * A级案件状态
     */
    private  String pja_1;
    /**
     * A级案件状态
     */
    private  String pja_2;
    /**
     * A级案件状态
     */
    private  String pja_3;
    /**
     * A级案件状态
     */
    private  String pja_4;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPass_exam() {
        return pass_exam;
    }

    public void setPass_exam(int pass_exam) {
        this.pass_exam = pass_exam;
    }

    public String getCertification_no() {
        return certification_no;
    }

    public void setCertification_no(String certification_no) {
        this.certification_no = certification_no;
    }

    public Date getCertification_date() {
        return certification_date;
    }

    public void setCertification_date(String certification_date) {
        if (certification_date != null && StringUtils.isNotEmpty(certification_date)) {
            if(certification_date.indexOf(" ")>0) certification_date = certification_date.substring(0, certification_date.indexOf(" "));
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(certification_date);
                this.certification_date = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getExpire_on() {
        return expire_on;
    }

    public void setExpire_on(String expire_on) {
        if (expire_on != null && StringUtils.isNotEmpty(expire_on)) {
            if(expire_on.indexOf(" ")>0) expire_on = expire_on.substring(0, expire_on.indexOf(" "));
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(expire_on);
                this.expire_on = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getBlitz() {
        return blitz;
    }

    public void setBlitz(String blitz) {
        this.blitz = blitz;
    }

    public String getPja_1() {
        return pja_1;
    }

    public void setPja_1(String pja_1) {
        this.pja_1 = pja_1;
    }

    public String getPja_2() {
        return pja_2;
    }

    public void setPja_2(String pja_2) {
        this.pja_2 = pja_2;
    }

    public String getPja_3() {
        return pja_3;
    }

    public void setPja_3(String pja_3) {
        this.pja_3 = pja_3;
    }

    public String getPja_4() {
        return pja_4;
    }

    public void setPja_4(String pja_4) {
        this.pja_4 = pja_4;
    }
}
