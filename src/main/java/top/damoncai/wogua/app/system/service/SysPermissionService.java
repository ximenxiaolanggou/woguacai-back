package top.damoncai.wogua.app.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.system.entity.SysPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(Integer userId);
}
