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


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends Model<Comment> {




    // 这个必须加，为了后面的回复评论要用到。
    private Long commentid;



    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */

    @TableField("`create_time`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;






    // 多表查询才定义的 一对一
    @TableField(exist = false)
    private User user;

    private Long uid;

    // 多表查询才定义的 一对一
    @TableField(exist = false)
    private Blog blog;

    private Long blogid;



            //        《写回复评论 功能》 新加的属性
         //     自身定义一个 所有的 《 子 》评论 的 对象属性         。所有的子评论都会被添加到这个children数组中
    @TableField(exist = false)
    private List<Comment> children;



    // 回复的用户id
    private int replyUid;

    // 回复的用户id
    private String replyUsername;


    // 回复的评论 id
    private int replyCommentid;

    // 是否为 根 评论
    private int root;



}
