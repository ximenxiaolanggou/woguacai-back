package top.damoncai.wogua.app.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.entity.Temp;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Mapper
public interface IotMapper extends BaseMapper<Iot> {

    /**
     * 通过SN 更新状态信息
     *
     * @param sn
     * @param online
     */
    void updateOnlineBySn(@Param("sn") String sn, @Param("online") Integer online);
}
