package top.damoncai.wogua.app.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.blog.entity.BlogArticleCategory;
import top.damoncai.wogua.app.blog.entity.BlogArticleTag;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:09
 */
public interface BlogArticleTagService extends IService<BlogArticleTag> {

    /**
     * 添加
     * @param articleId
     * @param tags
     */
    void save(Integer articleId, List<Integer> tags);

    /**
     * 根据文章ID删除
     * @param articleId
     */
    void deleteByArticleId(Integer articleId);

    /**
     * 根据标签删除
     * @param tagId
     */
    void deleteByTagId(Integer tagId);
}
