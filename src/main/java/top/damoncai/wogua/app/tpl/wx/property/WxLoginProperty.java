package top.damoncai.wogua.app.tpl.wx.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/8/26 10:53
 */

@Data
@Component
@ConfigurationProperties(prefix = "wx.login")
public class WxLoginProperty {

    /**
     * ID
     */
    private String clientId;

    /**
     * 秘钥
     */
    private String clientSecret;

    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 登录成功后回调
     */
    private String successRedirectUrl;
}
