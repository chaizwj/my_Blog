package com.example.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Comment;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 评论服务类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
public interface CommentService extends IService<Comment> {


    /**
     * 获取评论信息的列表
     *
     * @param blogid
     * @return
     */
    List<Comment> getCommentList(int blogid);

        /*
        添加评论
         */
    void addComment(Comment comment);



    IPage<Comment> getAllCommentList(int currentPage, int pageSize);

    void DeleteCommentById(Integer commentid);
}
