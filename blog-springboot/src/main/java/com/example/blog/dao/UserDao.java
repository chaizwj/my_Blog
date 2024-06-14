package com.example.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface UserDao extends BaseMapper<User> {


    @Select("SELECT * FROM user WHERE uid = #{id}")
    User getById(int id);




    // 带条件的 子查询 的返回分页Page对象

    @Results({@Result(column = "blogid",property = "blogid"),
            @Result(column = "blogid", property = "thumbs1",one = @One(select = "com.example.blog.dao.ThumbsDao.getById"))
    })

    @Select("select * from blog where blogid in (select blogid from thumbs where uid =#{uid})")
    IPage<Blog> find_thumbs(IPage<Blog> pagegation, int uid);



    @Results({@Result(column = "blogid",property = "blogid"),
            @Result(column = "blogid", property = "thumbs1",one = @One(select = "com.example.blog.dao.SaveDao.getById"))
    })
    @Select("select * from blog where blogid in (select blogid from saves where uid = #{uid})")
    IPage<Blog> find_saves(IPage<Blog> page, int uid);

    @Select("select * from user")
    IPage<User> getUsers(IPage<User> page);

    @Select("SELECT count(*) FROM user")
    int countUsersPie();
}
