package top.damoncai.wogua.app.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.damoncai.wogua.app.system.entity.SysPermission;
import top.damoncai.wogua.app.system.mapper.SysPermissionMapper;
import top.damoncai.wogua.app.system.service.SysPermissionService;

import java.util.ArrayList;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> findByUserId(Integer userId) {
        return permissionMapper.findByUserId(userId);
    }
}
