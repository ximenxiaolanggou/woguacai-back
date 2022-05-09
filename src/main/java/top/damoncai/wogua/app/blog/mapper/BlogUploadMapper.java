package top.damoncai.wogua.app.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.damoncai.wogua.app.blog.entity.BlogUpload;
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
public interface BlogUploadMapper extends BaseMapper<BlogUpload> {
}
