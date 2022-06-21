package top.damoncai.wogua.app.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.entity.Temp;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:39
 */
public interface IotService extends IService<Iot> {

    /**
     * 列表
     * @param searchKey
     * @return
     */
    List<Iot> list(String searchKey);

    /**
     * 根据SN查询
     * @param sn
     * @return
     */
    Iot findBySn(String sn);

    /**
     * 根据SN查询但忽略本身
     * @param sn
     * @param iotIds
     * @return
     */
    Iot findBySnIgnoreCur(String sn, List<Integer> iotIds);

    /**
     * 通过SN 更新状态信息
     * @param sn,
     * @param online,
     */
    void updateOnlineBySn(String clientId, Integer online);
}
