package top.damoncai.wogua.app.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.damoncai.wogua.app.blog.entity.BlogArticle;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {
}
