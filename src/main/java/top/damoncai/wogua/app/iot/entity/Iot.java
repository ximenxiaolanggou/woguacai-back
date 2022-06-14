package top.damoncai.wogua.app.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * IOT
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:49
 */
@Data
public class Iot extends Model<Iot> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String sn;

    /**
     * 0 离线 1 在线
     */
    private String online;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;



    @Override
    public Serializable pkVal() {
        return this.id;
    }
}