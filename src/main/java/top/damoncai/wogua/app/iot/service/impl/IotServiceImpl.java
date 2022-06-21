package top.damoncai.wogua.app.iot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.entity.Temp;
import top.damoncai.wogua.app.iot.mapper.IotMapper;
import top.damoncai.wogua.app.iot.mapper.TempMapper;
import top.damoncai.wogua.app.iot.service.IotService;
import top.damoncai.wogua.app.iot.service.TempService;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:40
 */
@Service
public class IotServiceImpl extends ServiceImpl<IotMapper, Iot> implements IotService {

    @Autowired
    private IotMapper iotMapper;

    /**
     * 通过SN 更新状态信息
     * @param sn
     * @param online
     */
    @Override
    public void updateOnlineBySn(String sn, Integer online) {
        iotMapper.updateOnlineBySn(sn,online);
    }

    /**
     * 根据SN查询但忽略本身
     * @param sn
     * @param iotIds
     * @return
     */
    @Override
    public Iot findBySnIgnoreCur(String sn, List<Integer> iotIds) {
        return iotMapper.selectOne(new LambdaQueryWrapper<Iot>().eq(Iot::getSn,sn).notIn(Iot::getId,iotIds));
    }

    /**
     * 根据SN查询
     * @param sn
     * @return
     */
    @Override
    public Iot findBySn(String sn) {
        return iotMapper.selectOne(new LambdaQueryWrapper<Iot>().eq(Iot::getSn,sn));
    }

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @Override
    public List<Iot> list(String searchKey) {
        LambdaQueryWrapper<Iot> qw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(searchKey)){
            qw.like(Iot::getSn,searchKey);
        }
        qw.orderByDesc(Iot::getCreatetime);
        return iotMapper.selectList(qw);
    }
}
