package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.domain.Blog;
import com.example.blog.util.WordsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.blog.domain.Comment;
import com.example.blog.dao.CommentDao;
import com.example.blog.service.CommentService;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论服务服务实现类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */



@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {


    @Autowired
    private CommentDao commentDao;

    //    获取所有评论 ,包括子评论。这里有一个《匹配》算法。插入到List<Comennt> children的这个数组里面
    public List<Comment> getCommentList(int blogid){


        List<Comment> comments = commentDao.getComments_BlogByIdWith_One_User(blogid);

        return comments;

    }


    // 也要过滤不当评论。
    @Override
    public void addComment(Comment comment) {

        comment.setContent(WordsFilter.Filter(comment.getContent()));
        commentDao.insert(comment);

    }



    @Override
    public IPage<Comment> getAllCommentList(int currentPage, int pageSize) {

        IPage<Comment> page = new Page(currentPage,pageSize);
        page = commentDao.getAllCommentList(page);
        return page;


    }



    @Override
    public void DeleteCommentById(Integer commentid) {


        QueryWrapper<Comment> qw = new QueryWrapper<>();
        qw.eq("commentid", commentid);
        commentDao.delete(qw);

    }


}
