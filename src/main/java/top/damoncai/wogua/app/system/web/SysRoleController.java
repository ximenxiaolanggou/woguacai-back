package top.damoncai.wogua.app.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.system.entity.SysRole;
import top.damoncai.wogua.app.system.entity.SysRolePermission;
import top.damoncai.wogua.app.system.entity.SysUserRole;
import top.damoncai.wogua.app.system.service.SysRolePermissionService;
import top.damoncai.wogua.app.system.service.SysRoleService;
import top.damoncai.wogua.app.system.service.SysUserRoleService;
import top.damoncai.wogua.app.system.service.SysUserService;
import top.damoncai.wogua.common.base.PageResult;
import top.damoncai.wogua.common.base.Result;
import top.damoncai.wogua.common.code.ResCode;
import top.damoncai.wogua.common.exception.ApiException;

import java.util.List;

@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRolePermissionService rolePermissionService;

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

    /**
     * 添加
     * @param sysRole
     * @return
     */
    @PostMapping
    public Result add(@RequestBody SysRole sysRole) {
        // 判断名称是否重复
        SysRole conditionRole = roleService.findByName(sysRole.getName());
        if(null != conditionRole) throw new ApiException(ResCode.ERROR_ROLE_EXIST);
        roleService.save(sysRole);
        return Result.ok();
    }

    /**
     * 修改
     * @param sysRole
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody SysRole sysRole,@PathVariable("id") Integer id) {
        // 判断名称是否重复
        SysRole conditionRole = roleService.findByNameIgnoreRoleId(sysRole.getName(),id);
        if(null != conditionRole) throw new ApiException(ResCode.ERROR_ROLE_EXIST);
        sysRole.setId(id);
        roleService.updateById(sysRole);
        return Result.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result update(@PathVariable("id") Integer id) {
        // 删除角色
        roleService.removeById(id);
        // 删除用户角色关系
        userRoleService.removeByRoleId(id);
        // 删除权限角色
        rolePermissionService.removeByRoleId(id);
        return Result.ok();
    }
}
