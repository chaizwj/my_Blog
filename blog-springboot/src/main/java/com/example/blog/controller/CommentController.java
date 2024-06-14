package com.example.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.util.MatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.Result;
import com.example.blog.domain.Comment;
import com.example.blog.domain.User;

import com.example.blog.service.CommentService;

import java.util.ArrayList;
import java.util.List;


/**
 * 评论模块
 *
 * @author fangjiale 2021年01月30日
 */


@RequestMapping("/comment")
@RestController

public class CommentController {

    @Autowired
    private CommentService commentService;


//   博客界面 ：     根据 blogid查询它对应的 所有评论
//  获取所有评论 ,包括《子》评论。（这里有一个    匹配算法 - - -工具类 MatchList的MatchChildren 方法 。 符合条件的才可以 插入到List<Comennt> children的这个数组里面）
    @GetMapping("/{blogid}")
    public Result getCommentList(@PathVariable int blogid) {


        return new Result(true,"ok", "获取评论列表信息成功",MatchList.matchChildren(commentService.getCommentList(blogid),commentService.getCommentList(blogid)));


    }

    @PostMapping
        public Result addComment(@RequestBody Comment comment)
    {


        commentService.addComment(comment);
        return new Result(true, "ok", "发表评论成功");

    }


    // 管理员后台的获取所有评论，进行对评论的管理
    @GetMapping("/getAllCommentList/{currentPage}/{pageSize}")
    public Result getAllCommentList(@PathVariable int currentPage,@PathVariable int pageSize) {

        IPage<Comment> page = commentService.getAllCommentList(currentPage, pageSize);
        return new Result(true,"ok", "获取分页数据成功", page);
    }

    @DeleteMapping("/{commentid}")
    public Result DeleteCommentById(@PathVariable Integer commentid){

        commentService.DeleteCommentById(commentid);
        return new Result(true,"ok", "评论删除成功");

    }

}
