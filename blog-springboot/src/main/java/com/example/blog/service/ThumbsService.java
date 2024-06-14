package com.example.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Thumbs;

public interface ThumbsService {


    Thumbs ifExist(int uid, int blogid);

    int delete(int uid, int blogid);


}
