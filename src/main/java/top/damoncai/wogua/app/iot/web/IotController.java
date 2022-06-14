package top.damoncai.wogua.app.iot.web;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.service.IotService;
import top.damoncai.wogua.common.base.Result;

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

    /**
     * 添加
     * @param iot
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Iot iot){
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
}
