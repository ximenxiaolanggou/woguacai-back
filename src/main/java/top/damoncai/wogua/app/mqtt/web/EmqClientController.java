package top.damoncai.wogua.app.mqtt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.iot.entity.Iot;
import top.damoncai.wogua.app.iot.service.IotService;
import top.damoncai.wogua.app.mqtt.client.EmqClient;
import top.damoncai.wogua.common.code.QosEnum;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/2/22 12:56
 */
@RestController
@RequestMapping("emq/client")
public class EmqClientController {

    @Autowired
    private EmqClient emqClient;

    @Autowired
    private IotService iotService;

    /**
     * 订阅主题
     */
    @PostConstruct
    public void postC() {
        List<Iot> iots = iotService.list();
        for (Iot iot : iots) {
            emqClient.subscribe(iot.getSn(),QosEnum.QoS0);
        }

    }

    /**
     * 连接
     * @param username
     * @param password
     * @return
     */
    @GetMapping("connect/{username}/{password}")
    public String connect(@PathVariable String username,
                          @PathVariable String password) {
        emqClient.connect(username,password);
        return "success ~~";
    }

    /**
     * 发布消息
     * @param msg
     * @return
     */
    @GetMapping("publish")
    public String publish(@RequestParam String msg) {
        emqClient.publish("test",msg, QosEnum.QoS0,false);
        return "消息发送成功 ~~";
    }

    /**
     * 订阅消息
     * @return
     */
    @GetMapping("subscribe")
    public String subscribe() {
        emqClient.subscribe("testtopic/#", QosEnum.QoS2);
        return "消息订阅成功 ~~";
    }

    /**
     * 关闭链接
     * @return
     */
    @GetMapping("disConnect")
    public String disConnect() {
        emqClient.disConnect();
        return "关闭链接成功 ~~";
    }
}
