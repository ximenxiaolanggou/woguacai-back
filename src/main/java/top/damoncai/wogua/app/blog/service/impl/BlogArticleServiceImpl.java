package top.damoncai.wogua.app.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.blog.entity.BlogArticle;
import top.damoncai.wogua.app.blog.mapper.BlogArticleMapper;
import top.damoncai.wogua.app.blog.service.BlogArticleService;
import top.damoncai.wogua.app.blog.vo.BlogArticleSearchVO;
import top.damoncai.wogua.common.base.PageResult;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:10
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;

    /**
     * 分页
     * @param pageNumber
     * @param pageSize
     * @param searchVO
     * @return
     */
    @Override
    public PageResult page(Integer pageNumber, Integer pageSize, BlogArticleSearchVO searchVO) {
        Page<BlogArticle> pageInfo = new Page<>(pageNumber, pageSize);
        IPage<BlogArticle> pr = articleMapper.page(pageInfo,searchVO);
        return new PageResult(pr.getTotal(), pr.getRecords());
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public BlogArticle findById(Integer id) {
        return articleMapper.findById(id);
    }
}

