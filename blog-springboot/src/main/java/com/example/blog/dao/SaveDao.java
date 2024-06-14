package com.example.blog.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.domain.Save;
import com.example.blog.domain.Thumbs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SaveDao extends BaseMapper<Save> {


    @Select("select * from saves WHERE blogid = #{blogid}")
    Thumbs getById(int blogid);

    @Select("select * from saves where uid=#{uid} and blogid=#{blogid}")
    Save ifExist(int uid, int blogid);


    @Delete("delete from saves where uid = #{uid} and blogid = #{blogid}")
    int delete(int uid, int blogid);

    // 插入一条thumbs 记录
    @Insert("insert into saves(blogid,uid) values(#{blogid},#{uid})")
    int insert_save_blogid_uid(int blogid, int uid);



}
