package top.damoncai.wogua.app.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.blog.entity.BlogCategory;
import top.damoncai.wogua.app.blog.entity.BlogTag;
import top.damoncai.wogua.app.blog.service.BlogArticleCategoryService;
import top.damoncai.wogua.app.blog.service.BlogArticleTagService;
import top.damoncai.wogua.app.blog.service.BlogCategoryService;
import top.damoncai.wogua.app.blog.service.BlogTagService;
import top.damoncai.wogua.common.base.Result;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 15:50
 */
@RestController
@RequestMapping("blogTag")
public class BlogTagController {

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private BlogArticleTagService articleTagService;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey) {
        List<BlogTag> tagList = tagService.list(searchKey);
        return Result.ok(tagList);
    }

    /**
     * 添加
     * @param blogTag
     * @return
     */
    @PostMapping
    public Result add(@RequestBody BlogTag blogTag) {
        tagService.save(blogTag);
        return Result.ok();
    }

    /**
     * 修改
     * @param blogTag
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody BlogTag blogTag,@PathVariable("id") Integer id) {
        blogTag.setId(id);
        tagService.updateById(blogTag);
        return Result.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @Transactional
    public Result delete(@PathVariable("id") Integer id) {
        // 删除类别
        tagService.removeById(id);
        // 删除文章标签关系
        articleTagService.deleteByTagId(id);
        return Result.ok();
    }
}
