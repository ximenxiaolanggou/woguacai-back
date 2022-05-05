package top.damoncai.wogua.app.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 列表
     * @param searchKey
     * @return
     */
    List<SysRole> list(String searchKey);
}
