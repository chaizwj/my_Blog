package com.example.blog.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.domain.Comment;
import com.example.blog.domain.Thumbs;
import com.example.blog.domain.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ThumbsDao extends BaseMapper<Thumbs> {



    @Select("select * from thumbs WHERE blogid = #{blogid}")
    Thumbs getById(int blogid);

    @Select("select * from thumbs where uid=#{uid} and blogid=#{blogid}")
    Thumbs ifExist(int uid, int blogid);



    @Delete("delete from thumbs where uid = #{uid} and blogid = #{blogid}")
    int delete(int uid, int blogid);


}
