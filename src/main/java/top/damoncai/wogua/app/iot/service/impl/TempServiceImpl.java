package top.damoncai.wogua.app.iot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.iot.entity.Temp;
import top.damoncai.wogua.app.iot.mapper.TempMapper;
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
public class TempServiceImpl extends ServiceImpl<TempMapper, Temp> implements TempService {

    @Autowired
    private TempMapper tempMapper;

    /**
     * 根据数据源和限定个数查询
     * @param source
     * @param limit
     * @return
     */
    @Override
    public List<Temp> listBySourceLimt(String source, int limit) {
        LambdaQueryWrapper<Temp> qw = new LambdaQueryWrapper<>();
        qw.eq(Temp::getSource,source)
                .orderByDesc(Temp::getCreatetime)
                .last("limit " + limit);
        return tempMapper.selectList(qw);
    }
}
