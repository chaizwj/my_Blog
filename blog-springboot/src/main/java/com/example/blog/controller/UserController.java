package com.example.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.Result;
import com.example.blog.domain.User;


/**
 * 评论模块
 *
 * @author fangjiale 2021年01月30日
 */


@RequestMapping("/user")
@RestController

public class UserController {




    @Autowired
    private UserService userService;


    //     判断
    @GetMapping("/login/{username}")
    public Result login(@PathVariable String username) {


        User user = userService.login(username);


        return new Result(true, "ok", "登入成功", user);

    }


    //    用户注册，插入用户
    @PostMapping
    public Result registerUser(@RequestBody User user) {


        userService.registerUser(user);

        return new Result(true, "ok", "注册成功");

    }


    @PutMapping
    public Result UpdateUser(@RequestBody User user) {


        userService.UpdateUser(user);

        return new Result(true, "ok", "更新用户信息成功");

    }


    //        获取某一个用户
    @GetMapping("/{uid}")
    public Result getUser(@PathVariable int uid) {

        return new Result(true, "ok", "查询用户信息成功", userService.getUser(uid));

    }

    // 查询   所有  用户
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getUsers(@PathVariable int currentPage, @PathVariable int pageSize) {

        return new Result(true, "ok", "查询用户信息成功", userService.getUsers(currentPage, pageSize));

    }


    //     查询 点赞 过的博客     .分页查询+带条件的 多表查询 (比较复杂)
    @GetMapping("/find_thumbs/{currentPage}/{pageSize}/{uid}")
    public Result find_thumbs(@PathVariable int currentPage, @PathVariable int pageSize, @PathVariable int uid) {
        System.out.println(uid);
        IPage<Blog> page = userService.find_thumbs(currentPage, pageSize, uid);
        return new Result(true, "ok", "获取分页数据成功", page);
    }


    //     查询 收藏 过的博客    .分页查询+带条件的 多表查询 (比较复杂)
    @GetMapping("/find_saves/{currentPage}/{pageSize}/{uid}")
    public Result find_saves(@PathVariable int currentPage, @PathVariable int pageSize, @PathVariable int uid) {

        IPage<Blog> page = userService.find_saves(currentPage, pageSize, uid);
        return new Result(true, "ok", "获取分页数据成功", page);


    }


    //  主页面的     ......根据 blogid 跳转 对应某个详细 博客的 方法
    @GetMapping("/find_blogs/{currentPage}/{pageSize}/{uid}")
    public Result find_blogs(@PathVariable int currentPage, @PathVariable int pageSize, @PathVariable int uid) {

        return new Result(true, "ok", "获取博客信息成功", userService.find_blogs(currentPage, pageSize, uid));

    }


}
