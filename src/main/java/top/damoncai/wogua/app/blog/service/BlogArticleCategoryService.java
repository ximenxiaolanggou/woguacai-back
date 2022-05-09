package top.damoncai.wogua.app.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.blog.entity.BlogArticle;
import top.damoncai.wogua.app.blog.entity.BlogArticleCategory;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:09
 */
public interface BlogArticleCategoryService extends IService<BlogArticleCategory> {

    /**
     * 添加
     * @param articleId
     * @param categories
     */
    void save(Integer articleId, List<Integer> categories);

    /**
     * 根据文章ID删除
     * @param articleId
     */
    void deleteByArticleId(Integer articleId);

    /**
     * 根据类别ID删除
     * @param categoryId
     */
    void deleteByCategoryId(Integer categoryId);
}
