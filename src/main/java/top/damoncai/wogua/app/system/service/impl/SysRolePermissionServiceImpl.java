package top.damoncai.wogua.app.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.damoncai.wogua.app.system.entity.SysRolePermission;
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

}
