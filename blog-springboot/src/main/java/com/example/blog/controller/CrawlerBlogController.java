package com.example.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.CrawledBlog;
import com.example.blog.entity.Result;
import com.example.blog.service.CrawlerBlogServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/crawler")
@RestController
public class CrawlerBlogController {



    @Autowired
    private CrawlerBlogServicce crawlerBlogServicce;

    @GetMapping("/findCrawler/{currentPage}/{pageSize}")
    public Result findCrawler(@PathVariable int currentPage, @PathVariable int pageSize){

        IPage<CrawledBlog> page = crawlerBlogServicce.findCrawler(currentPage, pageSize);
        return new Result(true,"ok", "获取分页数据成功", page);

    }


    @GetMapping("/{blogId}")
    public Result getOneBlog(@PathVariable int blogId) {

        return new Result(true,"ok", "获取博客信息成功",crawlerBlogServicce.getOneBlog(blogId));

    }





}
