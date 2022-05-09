package top.damoncai.wogua.app.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.damoncai.wogua.app.system.entity.SysPermission;
import top.damoncai.wogua.app.system.entity.SysRolePermission;
import top.damoncai.wogua.app.system.mapper.SysPermissionMapper;
import top.damoncai.wogua.app.system.mapper.SysRolePermissionMapper;
import top.damoncai.wogua.app.system.service.SysRolePermissionService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */

@Service
@Transactional
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;


    /**
     * 更具权限集合删除
     * @param delPerIds
     */
    @Override
    public void removeByPermissionIds(List<Integer> delPerIds) {
        if(CollectionUtil.isEmpty(delPerIds)) return;
        rolePermissionMapper.delete(new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getPermissionId,delPerIds));
    }

    /**
     * 添加
     * @param roldId
     * @param permissionIds
     */
    @Override
    public void add(Integer roldId, List<Integer> permissionIds) {
        if(null == roldId || CollectionUtil.isEmpty(permissionIds)) return;
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setRoleId(roldId);
        for (Integer permissionId : permissionIds) {
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }

    }

    /**
     * 根据角色ID删除
     * @param roleId
     */
    @Override
    public void removeByRoleId(Integer roleId) {
        rolePermissionMapper.delete(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId,roleId));
    }
}
