package com.example.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.example.blog.domain.Comment;

import java.util.List;

/**
 * <p>
 *  评论dao接口
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
@Mapper

public interface CommentDao extends BaseMapper<Comment> {

    // 显示每篇博客对应下的评论。。。本质上是多表 一对多 查询。返回 每一个Comment的所有属性，包含其含有的 多个 User的所有属性
    @Results({
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))

    })
    @Select("SELECT * FROM comment where blogid=#{blogid}")
    List<Comment> getComments_BlogByIdWith_One_User(int blogid);

    @Select("SELECT count(*) FROM comment")
    int sumComenntsPie();


    // 本质上是多表 一对多 查询。返回 每一个Comment的所有属性，包含其含有的 多个 User的所有属性
    @Results({
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById")),
            @Result(column = "blogid", property = "blog",one = @One(select = "com.example.blog.dao.BlogDao.getById"))

    })
    @Select("SELECT * FROM comment")
    IPage<Comment> getAllCommentList(IPage<Comment> pagegation);





}
