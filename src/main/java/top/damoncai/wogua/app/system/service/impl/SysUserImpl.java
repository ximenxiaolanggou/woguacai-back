package top.damoncai.wogua.app.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.system.entity.SysUser;
import top.damoncai.wogua.app.system.entity.SysUserRole;
import top.damoncai.wogua.app.system.mapper.SysUserMapper;
import top.damoncai.wogua.app.system.service.SysUserService;
import top.damoncai.wogua.common.base.PageResult;

@Service
public class SysUserImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 分页
     * @param searchKey
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageResult page(Integer pageNumber, Integer pageSize, String searchKey) {
        Page<SysUser> pageInfo = new Page<SysUser>(pageNumber, pageSize);
        IPage<SysUser> pageRes = userMapper.page(pageInfo,searchKey);
        return new PageResult(pageRes.getTotal(), pageRes.getRecords());
    }

    /**
     * 根据微信open id查询
     * @param openid
     * @return
     */
    @Override
    public SysUser findByWxOpenId(String openid) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getWxOpenId, openid));
    }

    /**
     * 根据手邮箱查询用户 并忽略 用户
     * @param mail
     * @param userId
     * @return
     */
    @Override
    public SysUser findByMailIngoreUserId(String mail, Integer userId) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMail,mail).notIn(SysUser::getId, userId));
    }

    /**
     * 根据手机号查询用户 并忽略 用户
     * @param mobile
     * @param userId
     * @return
     */
    @Override
    public SysUser findByMobileIngoreUserId(String mobile, Integer userId) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMobile,mobile).notIn(SysUser::getId, userId));
    }

    /**
     * 校验邮箱
     * @param mail
     * @return
     */
    @Override
    public SysUser findByMail(String mail) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMail,mail));
    }

    /**
     * 根据手机号查询用户
     * @return
     */
    @Override
    public SysUser findByMobile(String mobile) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMobile,mobile));
    }

    /**
     * 根据邮箱或手机号查询
     * @param mailOrMobile
     * @return
     */
    @Override
    public SysUser findByMailOrMobile(String mailOrMobile) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMobile,mailOrMobile).or().eq(SysUser::getMail,mailOrMobile));
    }
}
