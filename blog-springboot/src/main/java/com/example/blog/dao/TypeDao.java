package com.example.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.example.blog.domain.Type;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
@Mapper
public interface TypeDao extends BaseMapper<Type> {

    /**
     * 获取每个分类的博客数量
     * @return
     */
    @Select("SELECT typeid,typename from type")
    List<Type> getType();

    /**
     * 根据 typeid 获取 博客的种类。该方法用于多表查询
     * @returntype
     */

    @Select("SELECT * FROM type WHERE typeid = #{typeid}")
    Type getById(int typeid);


}
