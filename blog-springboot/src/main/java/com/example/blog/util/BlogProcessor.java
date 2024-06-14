package com.example.blog.util;

import com.example.blog.dao.BlogDao;
import com.example.blog.domain.Blog;
import com.example.blog.domain.CrawledBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 博客爬取类
 */
@Component
public class BlogProcessor implements PageProcessor{


    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());
        String title = page.getHtml().xpath("//*[@id=\"articleContentId\"]/text()").get();
        String content = page.getHtml().xpath("//*[@id=\"content_views\"]").get();
        String username = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/div/a[1]/text()").get();
        String views = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/div/span[2]/text()").get();
        String avatar = page.getHtml().xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a/").css("img", "src").get();  // 图片在img标签下的src里


        CrawledBlog crawledBlog = new CrawledBlog();

        //  必须要判空。否则整个程序不能执行。！！！！！ 困扰我了将近十天
        if(views != null)
        {

            crawledBlog.setTitle(title).setUsername(username).setViews(Integer.valueOf(views)).setAvatar(avatar).setContent(content);
        }

        else
        {
            crawledBlog.setTitle(title).setUsername(username).setViews(0).setAvatar(avatar).setContent(content);

        }

        System.out.println(crawledBlog);

        page.putField("crawledBlog",crawledBlog);

    }



    @Override
    public Site getSite() {

        return Site.me().setRetryTimes(300).setSleepTime(1000);
    }




}
