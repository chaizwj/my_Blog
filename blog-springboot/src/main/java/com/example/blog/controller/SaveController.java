package com.example.blog.controller;

import com.example.blog.entity.Result;
import com.example.blog.service.BlogService;
import com.example.blog.service.SaveService;
import com.example.blog.service.ThumbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/saves")
@RestController
public class SaveController {

    @Autowired
    SaveService saveService;


    @Autowired
    private BlogService blogService;

    // 查看 用户收藏是否已存在
    @GetMapping("/{uid}/{blogid}")
    public Result ifExist(@PathVariable int uid, @PathVariable int blogid) {

        return new Result(true,"ok", "获取是否存在 数据成功",saveService.ifExist(uid, blogid)==null);
    }



    // 删除 用户收藏
    @DeleteMapping("/{uid}/{blogid}")
    public Result delete(@PathVariable int uid,@PathVariable int blogid) {

        return new Result(true,"ok", "删除 用户收藏成功",saveService.delete(uid, blogid));
    }


    // 插入 收藏 记录
    @PostMapping("/{uid}/{blogid}")
    public Result insert(@PathVariable int uid,@PathVariable int blogid) {
        System.out.println(blogid);
        return new Result(true,"ok", "插入 收藏 记录成功",saveService.insert_save_blogid_uid(blogid, uid));

    }
}
