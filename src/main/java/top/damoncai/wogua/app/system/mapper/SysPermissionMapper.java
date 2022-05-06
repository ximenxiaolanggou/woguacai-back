package top.damoncai.wogua.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.damoncai.wogua.app.system.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(@Param("userId") Integer userId);
}