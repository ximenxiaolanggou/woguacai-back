package top.damoncai.wogua.app.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.blog.entity.BlogArticle;
import top.damoncai.wogua.app.blog.vo.BlogArticleSearchVO;
import top.damoncai.wogua.common.base.PageResult;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:09
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 分页
     * @param pageNumber
     * @param pageSize
     * @param searchVO
     * @return
     */
    PageResult page(Integer pageNumber, Integer pageSize, BlogArticleSearchVO searchVO);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    BlogArticle findById(Integer id);
}
