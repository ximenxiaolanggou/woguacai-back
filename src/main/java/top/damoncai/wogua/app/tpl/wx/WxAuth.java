package top.damoncai.wogua.app.tpl.wx;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.auth.dto.TokenInfoDTO;
import top.damoncai.wogua.app.auth.vo.LoginVO;
import top.damoncai.wogua.app.system.entity.SysUser;
import top.damoncai.wogua.app.system.service.SysUserService;
import top.damoncai.wogua.app.tpl.wx.property.WxLoginProperty;
import top.damoncai.wogua.common.base.Result;
import top.damoncai.wogua.common.code.ResCode;
import top.damoncai.wogua.common.exception.ApiException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 微信开放平台登录
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/8/26 10:51
 */
@RestController
@RequestMapping("wx/auth")
public class WxAuth {

    @Autowired
    private WxLoginProperty wxLoginProperty;

    @Autowired
    private SysUserService userService;

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public void login(AuthCallback callback, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse authResponse = authRequest.login(callback);
        AuthUser authUser = (AuthUser) authResponse.getData();
        // 获取open id
        JSONObject rawUserInfo = authUser.getRawUserInfo();
        String openid = rawUserInfo.getString("openid");
        // 根据open id 查询
        SysUser sysUser = userService.findByWxOpenId(openid);
        String successRedirectUrl = wxLoginProperty.getSuccessRedirectUrl();
        successRedirectUrl = successRedirectUrl + "?bind=";
        if(sysUser != null) {// 绑定过
            sysUser.setPassword(null);
            StpUtil.login(sysUser.getId(),"PC");
            StpUtil.getSession().set("user", sysUser);
            TokenInfoDTO tokenInfoDTO = new TokenInfoDTO(StpUtil.getTokenName(), StpUtil.getTokenValue());
            successRedirectUrl = successRedirectUrl + "true&tokenName=" + tokenInfoDTO.getTokenName() + "&tokenValue=" + tokenInfoDTO.getTokenValue();
        }else { // 未绑定过
            successRedirectUrl = successRedirectUrl + "false&openId=" + openid;
        }
        response.sendRedirect(successRedirectUrl);
    }

    private  AuthRequest getAuthRequest() {
        return new AuthWeChatOpenRequest(AuthConfig.builder()
                .clientId(wxLoginProperty.getClientId())
                .clientSecret(wxLoginProperty.getClientSecret())
                .redirectUri(wxLoginProperty.getCallbackUrl())
                .build());
    }


    /**
     * 登录
     * @param loginVo
     * @return
     */
    @PostMapping("bind/{openId}")
    public Result login(@RequestBody LoginVO loginVo, @PathVariable("openId") String openId) {
        SysUser sysUser = userService.findByMailOrMobile(loginVo.getUsername());
        if(sysUser == null || !BCrypt.checkpw(loginVo.getPassword(),sysUser.getPassword())) throw new ApiException(ResCode.ERROR_USERNAME_OR_PWD);
        StpUtil.login(sysUser.getId(),"PC");
        StpUtil.getSession().set("user", sysUser);
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO(StpUtil.getTokenName(), StpUtil.getTokenValue());
        // 更新 wx open id
        sysUser.setWxOpenId(openId);
        userService.updateById(sysUser);
        return Result.ok(tokenInfoDTO);
    }
}
