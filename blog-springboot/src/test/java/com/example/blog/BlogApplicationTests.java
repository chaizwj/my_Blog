package com.example.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.controller.BlogController;
import com.example.blog.controller.HomeController;
import com.example.blog.dao.BlogDao;
import com.example.blog.dao.CrawlerBlogDao;
import com.example.blog.dao.UserDao;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Comment;
import com.example.blog.domain.CrawledBlog;
import com.example.blog.domain.Type;
import com.example.blog.service.*;


import com.example.blog.util.BlogProcessor;
import com.example.blog.util.DbPipeline;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BlogApplicationTests {


    @Autowired
    CrawlerBlogDao crawlerBlogDao;

    @Autowired
    private ThumbsService thumbsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogDao blogDao;

   

    @Test
    void random1() {
        System.out.println(userDao.getById(1));

    }






    @Test
    void a() {




    }


    //  !!!PS: 这里，必须要进行一个Autowired 自动注入 。
    @Autowired
    private DbPipeline dbPipeline;
    //  !!!PS: 这里，必须要进行一个Autowired 自动注入 。
    @Autowired
    private BlogProcessor blogProcessor;


    @Test
    void crawler() {
            // 爬虫的入口。
        Spider.create(blogProcessor).
        addUrl("https://blog.csdn.net/").addPipeline(dbPipeline).run();
                //  取代 new DbPipeline()的方式。这样才能正确注入 Pipeline里面的 BlogDao 持久层。才能顺利插入数据库

    }
}
