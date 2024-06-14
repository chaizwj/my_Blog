package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.dao.CrawlerBlogDao;
import com.example.blog.domain.Blog;
import com.example.blog.domain.CrawledBlog;
import com.example.blog.service.CrawlerBlogServicce;
import com.example.blog.util.BlogProcessor;
import com.example.blog.util.DbPipeline;
import com.example.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

@Service
public class CrawlerBlogServicceImpl implements CrawlerBlogServicce {




    @Autowired
    CrawlerBlogDao crawlerBlogDao;

    @Override
    public IPage<CrawledBlog> findCrawler(int currentPage, int pageSize) {
        IPage<CrawledBlog> page = new Page(currentPage,pageSize);
        page = crawlerBlogDao.findCrawler(page);
        return page;
    }

    @Override
    public CrawledBlog getOneBlog(int blogId) {
        CrawledBlog crawledBlog = crawlerBlogDao.getOneBlog(blogId);
        crawledBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(crawledBlog.getContent()));
        return crawledBlog;
    }


}
