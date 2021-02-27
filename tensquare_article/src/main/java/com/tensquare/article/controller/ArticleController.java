package com.tensquare.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // POST /article/search/{page}/{size} 文章分页
    @PostMapping("search/{page}/{size}")
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer size,
                             @RequestBody Map<String, Object> map) {
        //根据条件分页查询
        Page<Article> pageData = articleService.findByPage(map, page, size);
        //封装分页返回对象
        PageResult<Article> pageResult = new PageResult<>(pageData.getTotal(), pageData.getRecords());
        //返回数据
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    // DELETE /article/{articleId} 根据ID删除文章
    @DeleteMapping("{articleId}")
    public Result deleteById(@PathVariable String articleId) {
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    // PUT /article/{articleId} 修改文章
    @PutMapping("{articleId}")
    public Result updateById(@PathVariable String articleId,
                             @RequestBody Article article) {
        article.setId(articleId);//设置id
        articleService.updateById(article);// 修改
        return new Result(true, StatusCode.OK, "修改成功");
    }

    // POST /article 增加文章
    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "新增成功");
    }


    // GET /article/{articleId} 根据ID查询文章
    @GetMapping("{articleId}")
    public Result findById(@PathVariable String articleId) {
        Article article = articleService.findById(articleId);
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    // GET /article 文章全部列表
    @GetMapping
    public Result findAll() {
        List<Article> list = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
