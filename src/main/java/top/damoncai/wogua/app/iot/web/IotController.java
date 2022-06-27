package top.damoncai.wogua.app.iot.web;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.service.IotService;
import top.damoncai.wogua.app.mqtt.client.EmqClient;
import top.damoncai.wogua.common.base.Result;
import top.damoncai.wogua.common.code.QosEnum;
import top.damoncai.wogua.common.code.ResCode;
import top.damoncai.wogua.common.exception.ApiException;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:42
 */
@RestController
@RequestMapping("iot")
public class IotController {

    @Autowired
    private IotService iotService;

    @Autowired
    private EmqClient emqClient;

    /**
     * 添加
     * @param iot
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Iot iot){
        // 判断SN是否存在
        Iot iotBySn = iotService.findBySn(iot.getSn());
        if(null != iotBySn) throw new ApiException(ResCode.ERROR_SN_EXIST);
        iotService.save(iot);
        return Result.ok();
    }

    /**
     * 修改
     * @param iot
     * @param id
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody Iot iot, @PathVariable Integer id){
        // 判断SN是否重复
        Iot iotBySnIgnoreCur = iotService.findBySnIgnoreCur(iot.getSn(), Arrays.asList(id));
        if(null != iotBySnIgnoreCur) throw new ApiException(ResCode.ERROR_SN_EXIST);
        iot.setId(id);
        iotService.updateById(iot);
        return Result.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result remove(@PathVariable Integer id){
        iotService.removeById(id);
        return Result.ok();
    }

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey){
        List<Iot> iots = iotService.list(searchKey);
        return Result.ok(iots);
    }

    /**
     * 开关LED灯
     * @param openOrClose
     * @return
     */
    @PutMapping("openOrCloseLed/{id}")
    public Result openOrCloseLed(boolean openOrClose){
        if(openOrClose) { // 开灯
            emqClient.publish("test","2", QosEnum.QoS0,false);
        }else{ // 关灯
            emqClient.publish("test","1", QosEnum.QoS0,false);
        }
        return Result.ok();
    }
}
