package top.damoncai.wogua.app.iot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.iot.entity.Temp;
import top.damoncai.wogua.app.iot.mapper.TempMapper;
import top.damoncai.wogua.app.iot.service.TempService;

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
}