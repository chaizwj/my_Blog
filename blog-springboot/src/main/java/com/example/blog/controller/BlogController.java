package com.example.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.service.ThumbsService;
import com.example.blog.util.DbPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.Result;
import com.example.blog.domain.User;

import com.example.blog.service.BlogService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 分类管理模块
 *
 * @author fangjiale 2021年01月27日
 */
@RequestMapping("/blog")
@RestController


public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private ThumbsService thumbsService;


//  主页面的     ......根据 blogid 跳转 对应某个详细 博客的 方法
    @GetMapping("/{blogId}")
    public Result getOneBlog(@PathVariable int blogId) {

        return new Result(true,"ok", "获取博客信息成功",blogService.getOneBlog(blogId));

    }


    //  博主管理页面 的     ......   根据 blogid 跳转 对应某个详细 博客的 ,多表查询,带 type
    @GetMapping("/admin/{blogId}")
    public Result get_admin_One_Blog_with_typeName(@PathVariable int blogId) {

        return new Result(true,"ok", "获取博客信息成功",blogService.get_admin_One_Blog_with_typeName(blogId));

    }

    @PutMapping("/views")
    public Result addViews(@RequestBody Blog blog){

        blogService.addViews(blog);
        return new Result(true,"ok", "博客浏览次数加一");

    }







    @PutMapping("/Thumbs/{blogid}/{uid}")
    public Result addThumbs(@RequestBody Blog blog,@PathVariable int blogid,@PathVariable int uid){

        //    blog表更新 thumbs属性
        blogService.addThumbs(blog);


        return new Result(true,"ok", "博客点赞数加一");

    }






    @PostMapping
    public Result addBlogs(@RequestBody Blog blog){

        blogService.addBlogs(blog);
        return new Result(true,"ok", "博客添加成功");

    }








    //    admin 博主管理页面的。带分页的  多表查询 blog+type。就是为了得到type中对应的typeName
    @GetMapping("/findAdminHomePage/{currentPage}/{pageSize}")
    public Result findAdminHomePage(@PathVariable int currentPage,@PathVariable int pageSize) {
        IPage<Blog> page = blogService.getBlogs_with_typeName(currentPage, pageSize);
        return new Result(true,"ok", "获取分页数据成功", page);
    }






    @PutMapping
    public Result UpdateBlogs(@RequestBody Blog blog){

        blogService.UpdateBlogs(blog);
        return new Result(true,"ok", "博客更新成功");

    }


    @DeleteMapping("/{blogid}")
    public Result DeleteBlogById(@PathVariable Integer blogid){
        System.out.println(blogid);
        blogService.DeleteBlogById(blogid);
        return new Result(true,"ok", "博客删除成功");

    }








}
