package io.renren.modules.position.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("position")
public class PositionEntity {
    /**
     * 职称表<position>主键
     */
    private  int id;
    /**
     * 职称
     */
    private  String plant_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant_type() {
        return plant_type;
    }

    public void setPlant_type(String plant_type) {
        this.plant_type = plant_type;
    }
}
