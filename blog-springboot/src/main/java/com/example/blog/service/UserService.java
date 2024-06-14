package com.example.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Thumbs;
import com.example.blog.domain.User;

import java.util.List;

public interface UserService {


    /*
     检验用户登入的密码是否正确 ，返回一个对象，包含token还有正确 账户密码，交给前端去验证
     */
    User login(String username);

    void registerUser(User user);

    void UpdateUser(User user);



    IPage<Blog> find_thumbs(int currentPage, int pageSize,int uid);

    IPage<Blog> find_saves(int currentPage, int pageSize, int uid);

    User getUser(int uid);


    IPage<Blog> find_blogs(int currentPage, int pageSize, int uid);

    IPage<User> getUsers(int currentPage, int pageSize);
}
