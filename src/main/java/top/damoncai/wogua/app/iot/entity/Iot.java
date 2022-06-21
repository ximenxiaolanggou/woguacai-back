package top.damoncai.wogua.app.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import top.damoncai.wogua.common.ivalidation.Insert;
import top.damoncai.wogua.common.ivalidation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
     * sn
     */
    @NotBlank(message = "SN不可为空", groups = {Insert.class, Update.class})
    private String sn;

    /**
     * 协议类型 1 MQTT Borker, 2 TCP, 3 UDP, 4 HTTP
     */
    @NotNull(message = "协议类型不可为空", groups = {Insert.class, Update.class})
    private Integer protocolType;

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