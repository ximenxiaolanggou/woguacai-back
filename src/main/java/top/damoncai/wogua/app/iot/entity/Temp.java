package top.damoncai.wogua.app.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 温度
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:35
 */
@Data
@TableName("iot_temp")
public class Temp extends Model<Temp> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String temp;

    /**
     * 1 C51 2 stm32
     */
    private String source;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
