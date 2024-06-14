package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.blog.domain.Blog;
import com.example.blog.dao.BlogDao;
import com.example.blog.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService  {

//        自动注入，也就是初始化，给接口初始化，这样之后才能使用对象的方法
    @Autowired
    private BlogDao blogDao;

    public List<Blog> getArchivesList() {

        Page<Blog> page = new Page<>(1, 100);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select("blog_id", "title", "create_time", "first_picture")
                .orderByDesc("create_time");
        return blogDao.For_Archives_get_Blogs_with_user();


    }

}
