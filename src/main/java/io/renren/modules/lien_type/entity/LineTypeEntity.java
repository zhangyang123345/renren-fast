package io.renren.modules.lien_type.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("line_type")
public class LineTypeEntity {
    /**
     * 主键ID
     */
    private int id;

    /**
     * 线别类型
     */
    private String lien_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLien_type() {
        return lien_type;
    }

    public void setLien_type(String lien_type) {
        this.lien_type = lien_type;
    }
}
