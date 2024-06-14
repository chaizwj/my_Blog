package com.example.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.CrawledBlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CrawlerBlogDao extends BaseMapper<CrawledBlog> {

    @Select("SELECT * FROM crawlerblog")
    IPage<CrawledBlog> findCrawler(IPage<CrawledBlog> pagegation);

    @Insert("insert into crawlerblog(title,content,username,views,avatar) values(#{title},#{content},#{username},#{views},#{avatar})")
    int add_crawler_blogs(CrawledBlog crawledBlog);

    @Select("SELECT * FROM crawlerblog where blogid=#{blogid}")
    CrawledBlog getOneBlog(int blogid);
}
