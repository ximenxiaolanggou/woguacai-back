package top.damoncai.wogua.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.damoncai.wogua.app.system.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 分页
     *
     * @param pageInfo
     * @param searchKey
     * @return
     */
    IPage<SysUser> page(Page<SysUser> pageInfo, @Param("searchKey") String searchKey);
}
