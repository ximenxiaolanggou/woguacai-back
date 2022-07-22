package top.damoncai.wogua.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.entity.Temp;
import top.damoncai.wogua.app.iot.service.IotService;
import top.damoncai.wogua.app.iot.service.TempService;
import top.damoncai.wogua.common.base.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private TempService tempService;

    @Autowired
    private IotService iotService;

    /**
     * 温度
     * @return
     */
    @GetMapping("temp/{id}")
    public Result temp(@PathVariable("id") int id) {
        Iot iot = iotService.getById(id);
        List<Temp> temps = tempService.listBySourceLimt(iot.getSn(),30);
        return Result.ok(temps);
    }
}
