package top.damoncai.wogua.app.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.blog.entity.BlogArticle;
import top.damoncai.wogua.app.blog.mapper.BlogArticleMapper;
import top.damoncai.wogua.app.blog.service.BlogArticleService;

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
}
