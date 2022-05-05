package top.damoncai.wogua.app.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.damoncai.wogua.app.system.entity.SysRole;
import top.damoncai.wogua.app.system.mapper.SysRoleMapper;
import top.damoncai.wogua.app.system.service.SysRoleService;

import java.util.List;
import java.util.stream.Collectors;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @Override
    public List<SysRole> list(String searchKey) {
        LambdaQueryWrapper<SysRole> qw = new LambdaQueryWrapper<SysRole>();
        if(StringUtils.isNotBlank(searchKey)) qw.like(SysRole::getName, searchKey);
        qw.orderByDesc(SysRole::getCreatetime);
        return roleMapper.selectList(qw);
    }
}
