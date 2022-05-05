package top.damoncai.wogua.app.auth.web;
import cn.dev33.satoken.stp.StpUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.auth.dto.AuthUserDTO;
import top.damoncai.wogua.app.auth.dto.TokenInfoDTO;
import top.damoncai.wogua.app.auth.vo.LoginVO;
import top.damoncai.wogua.app.system.entity.SysPermission;
import top.damoncai.wogua.app.system.entity.SysUser;
import top.damoncai.wogua.app.system.service.SysPermissionService;
import top.damoncai.wogua.app.system.service.SysUserService;
import top.damoncai.wogua.common.base.Result;
import top.damoncai.wogua.common.code.ResCode;
import top.damoncai.wogua.common.exception.ApiException;
import top.damoncai.wogua.common.util.BCyptUtil;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 认证类
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @GetMapping("test")
    public String test() {
        return "Success ~~";
    }

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginVO loginVo) {
        SysUser sysUser = userService.findByMailOrMobile(loginVo.getUsername());
        if(sysUser == null || !BCrypt.checkpw(loginVo.getPassword(),sysUser.getPassword())) throw new ApiException(ResCode.ERROR_USERNAME_OR_PWD);
        StpUtil.login(sysUser.getId(),"PC");
        StpUtil.getSession().set("user", sysUser);
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO(StpUtil.getTokenName(), StpUtil.getTokenValue());
        return Result.ok(tokenInfoDTO);
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping("userInfo")
    public Result userInfo() {
        Integer userId = null;
        try {
            userId = StpUtil.getLoginIdAsInt();
        }catch (Exception e) {
            throw new ApiException(ResCode.ERROR_AUTH_EXPIRED);
        }
        SysUser user = userService.getById(userId);
        List<String> permissions = getUserPermissions(userId);
        AuthUserDTO authUserDTO = generateAutherUser(user,permissions);
        return Result.ok(authUserDTO);
    }

    /**
     * 登出
     * @return
     */
    @DeleteMapping("logout")
    public Result logout() {
        try {
            StpUtil.logout();
        }catch (Exception ex) {
            throw new ApiException(ResCode.ERROR_NOT_LOGIN);
        }
        return Result.ok();
    }

    /**
     * 生成认证用户
     * @param user
     * @param permissions
     * @return
     */
    private AuthUserDTO generateAutherUser(SysUser user, List<String> permissions) {
        AuthUserDTO authUser = new AuthUserDTO();
        authUser.setUsername(user.getUsername());
        authUser.setMail(user.getMail());
        authUser.setMobile(user.getMobile());
        authUser.setPermissions(permissions);
        return authUser;
    }

    /**
     * 获取用户权限集合
     * @param userId
     * @return
     */
    private List<String> getUserPermissions(Integer userId) {
        List<SysPermission> permissions = permissionService.findByUserId(userId);
        return permissions.stream().map(SysPermission::getCode).distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] passwordAndSalt = BCyptUtil.getPasswordAndSalt("123");
        System.out.println(passwordAndSalt[0]);
    }
}
