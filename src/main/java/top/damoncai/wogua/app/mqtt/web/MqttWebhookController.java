package top.damoncai.wogua.app.mqtt.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.damoncai.wogua.app.iot.service.IotService;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/21 16:48
 */
@Slf4j
@RestController
@RequestMapping("mqtt")
public class MqttWebhookController {

    @Autowired
    private IotService iotService;

    @PostMapping("webhook")
    public void webhook(@RequestBody Map map) {
        String action = String.valueOf(map.get("action"));
        String clientId = String.valueOf(map.get("clientid"));
        if("client_connected".equals(action)){
            iotService.updateOnlineBySn(clientId,1);
        }
        log.info("data: " + map);
    }
}
