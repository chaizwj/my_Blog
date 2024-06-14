package com.example.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 博客实体类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-27
 */
@Data

public class Blog extends Model<Blog> {


    /**
     * 博客id
     */


    private Long blogid;






    /**
     * 评论状态
     */
    private boolean commentabled;

    /**
     * 内容
     */
    private String content;



    /**
     * 首图
     */
    private String firstpicture;

    /**
     * 点赞数 ，这个只是一个属性。
     */
    private Integer thumbs;

    /**
     * 发布状态
     */
    private boolean published;

    /**
     * 推荐状态
     */
    private boolean recommend;

    /**
     * 版权状态
     */
    @TableField("shareStatement")
    private String shareStatement;

    /**
     * 标题
     */
    private String title;


    /**
     * 浏览次数
     */
    private Integer views;


    // 多表查询要用的
    private Long uid;

    /**
     * 博客摘要
     */
    private String description;






    /**
     * 创建时间
     */

    @TableField("createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;


    // 一对一的多表查询 必须外带 另一个表对应的实体类
    @TableField(exist = false)
    private User user;




    // 一对一的多表查询 必须外另外一个从表的 主键属性 也要加入
    private int typeid;
    // 一对一的多表查询 必须外带 另一个表对应的实体类
    @TableField(exist = false)
    private Type type;





    // 一对多 。多表查询 必须外带 另一个表对应的实体类
    @TableField(exist = false)
    private Thumbs thumbs1;







}
