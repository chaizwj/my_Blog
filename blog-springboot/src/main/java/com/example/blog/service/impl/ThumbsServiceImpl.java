package com.example.blog.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.dao.ThumbsDao;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Thumbs;
import com.example.blog.service.ThumbsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThumbsServiceImpl implements ThumbsService {

    @Autowired
    ThumbsDao thumbsDao;


    @Override
    public Thumbs ifExist(int uid, int blogid) {
        return thumbsDao.ifExist(uid, blogid);
    }

    @Override
    public int delete(int uid, int blogid) {
        return thumbsDao.delete(uid,blogid);

    }



}

