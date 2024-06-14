package com.example.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.dao.BlogDao;
import com.example.blog.dao.ThumbsDao;
import com.example.blog.dao.UserDao;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Thumbs;
import com.example.blog.domain.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-26
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private ThumbsDao thumbsDao;


    @Override
    public User login(String username) {

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        return userDao.selectOne(qw);


    }



    @Override
    public void registerUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void UpdateUser(User user) {

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("uid",user.getUid());
        userDao.update(user,qw);

    }







    public IPage<Blog> find_thumbs(int currentPage, int pageSize, int uid) {


        IPage<Blog> page = new Page(currentPage,pageSize);


        page = userDao.find_thumbs(page,uid);

        return page;


    }

    @Override
    public IPage<Blog> find_saves(int currentPage, int pageSize, int uid) {

        IPage<Blog> page = new Page(currentPage,pageSize);
        // 如果要分页。 必须传入这个page 到Dao层的方法中作为形参。
        page = userDao.find_saves(page,uid);

        return page;
    }

    @Override
    public User getUser(int uid) {
       return userDao.getById(uid);
    }




    public IPage<Blog> find_blogs(int currentPage, int pageSize, int uid) {

        IPage<Blog> page = new Page<>(currentPage, pageSize);
        page = blogDao.find_blogs(page,uid);
        return  page;
    }


    @Override
    public IPage<User> getUsers(int currentPage, int pageSize) {

        IPage<User> page = new Page<>(currentPage, pageSize);
        page = userDao.getUsers(page);
        return  page;

    }


}
