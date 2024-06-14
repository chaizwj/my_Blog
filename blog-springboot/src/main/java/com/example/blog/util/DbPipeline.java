package com.example.blog.util;

import com.example.blog.dao.BlogDao;
import com.example.blog.dao.CrawlerBlogDao;
import com.example.blog.domain.CrawledBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 将爬取到的数据    存入数据库
 */

// 这个@Component注解必须要加( 交由Spring管理 )！！！！否则 BlogDao 自动注入不了
@Component
public class DbPipeline implements Pipeline {



    @Autowired
    CrawlerBlogDao crawlerBlogDao;

    @Override
    public void process(ResultItems resultItems, Task task) {


        CrawledBlog crawledBlog = resultItems.get("crawledBlog");


        //  插入数据库
        crawlerBlogDao.add_crawler_blogs(crawledBlog);


    }
}
