package com.example.blog.controller;




import com.example.blog.service.ThumbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.Result;

import com.example.blog.service.BlogService;

/**
 *
 *
 * @author fangjiale 2021年01月27日
 */
@RequestMapping("/thumbs")
@RestController


public class ThumbsController {

    @Autowired
    ThumbsService thumbsService;


    @Autowired
    private BlogService blogService;

    // 查看 用户点赞 是否已存在
    @GetMapping("/{uid}/{blogid}")
    public Result ifExist(@PathVariable int uid,@PathVariable int blogid) {


        return new Result(true,"ok", "获取是否存在 数据成功",thumbsService.ifExist(uid, blogid)==null);
    }



    // 删除 用户点赞
    @DeleteMapping("/{uid}/{blogid}")
    public Result delete(@PathVariable int uid,@PathVariable int blogid) {




        return new Result(true,"ok", "获取是否存在 数据成功",thumbsService.delete(uid, blogid));
    }


    // 插入 点赞 记录
    @PostMapping("/{uid}/{blogid}")
    public Result insert(@PathVariable int uid,@PathVariable int blogid) {



        return new Result(true,"ok", "获取是否存在 数据成功",blogService.insertthumbs_blogid_uid(blogid, uid));



    }





}
