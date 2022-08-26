package top.damoncai.wogua.app.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.system.entity.SysUser;
import top.damoncai.wogua.common.base.PageResult;

public interface SysUserService extends IService<SysUser> {

    /**
     * 根据邮箱或手机号查询
     * @param mailOrMobile
     * @return
     */
    SysUser findByMailOrMobile(String mailOrMobile);

    /**
     * 分页
     * @param searchKey
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageResult page(Integer pageNumber, Integer pageSize, String searchKey);

    /**
     * 根据手机号查询用户
     * @return
     */
    SysUser findByMobile(String mobile);

    /**
     * 校验邮箱
     * @param mail
     * @return
     */
    SysUser findByMail(String mail);

    /**
     * 根据手机号查询用户 并忽略 用户
     * @param mobile
     * @param userId
     * @return
     */
    SysUser findByMobileIngoreUserId(String mobile, Integer userId);

    /**
     * 根据手邮箱查询用户 并忽略 用户
     * @param mail
     * @param userId
     * @return
     */
    SysUser findByMailIngoreUserId(String mail, Integer userId);

    /**
     * 根据微信open id查询
     * @param openid
     * @return
     */
    SysUser findByWxOpenId(String openid);
}
