package com.example.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.CrawledBlog;

public interface CrawlerBlogServicce {


    IPage<CrawledBlog> findCrawler(int currentPage, int pageSize);


    CrawledBlog getOneBlog(int blogId);
}
