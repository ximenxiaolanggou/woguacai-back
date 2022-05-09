package top.damoncai.wogua.app.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.damoncai.wogua.app.blog.entity.BlogArticle;
import top.damoncai.wogua.app.blog.entity.BlogCategory;
import top.damoncai.wogua.app.blog.properties.BlogProperties;
import top.damoncai.wogua.app.blog.service.BlogArticleCategoryService;
import top.damoncai.wogua.app.blog.service.BlogArticleService;
import top.damoncai.wogua.app.blog.service.BlogArticleTagService;
import top.damoncai.wogua.app.blog.service.BlogCategoryService;
import top.damoncai.wogua.common.base.Result;
import top.damoncai.wogua.common.exception.ApiException;

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
@RequestMapping("blogCategory")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogArticleCategoryService articleCategoryService;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey) {
        List<BlogCategory> categoryList = categoryService.list(searchKey);
        return Result.ok(categoryList);
    }

    /**
     * 添加
     * @param blogCategory
     * @return
     */
    @PostMapping
    public Result add(@RequestBody BlogCategory blogCategory) {
        categoryService.save(blogCategory);
        return Result.ok();
    }

    /**
     * 修改
     * @param blogCategory
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody BlogCategory blogCategory,@PathVariable("id") Integer id) {
        blogCategory.setId(id);
        categoryService.updateById(blogCategory);
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
        categoryService.removeById(id);
        // 删除文章类别关系
        articleCategoryService.deleteByCategoryId(id);
        return Result.ok();
    }
}
