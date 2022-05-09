package top.damoncai.wogua.app.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.damoncai.wogua.app.blog.entity.BlogCategory;
import top.damoncai.wogua.app.blog.entity.BlogTag;
import top.damoncai.wogua.app.blog.mapper.BlogCategoryMapper;
import top.damoncai.wogua.app.blog.mapper.BlogTagMapper;
import top.damoncai.wogua.app.blog.service.BlogCategoryService;
import top.damoncai.wogua.app.blog.service.BlogTagService;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:10
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {
}
