package top.damoncai.wogua.app.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.damoncai.wogua.app.system.entity.SysRole;
import top.damoncai.wogua.app.system.service.SysRoleService;
import top.damoncai.wogua.app.system.service.SysUserRoleService;
import top.damoncai.wogua.app.system.service.SysUserService;
import top.damoncai.wogua.common.base.PageResult;
import top.damoncai.wogua.common.base.Result;

import java.util.List;

@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey) {
        List<SysRole> roles = roleService.list(searchKey);
        return Result.ok(roles);
    }

}
