package com.example.blog.util;

import com.example.blog.domain.Comment;

import java.util.ArrayList;
import java.util.List;

//  获取所有评论 ,包括《子》评论。（这里有一个    匹配算法 - - -工具类 MatchList的MatchChildren 方法 。 符合条件的才可以 插入到List<Comennt> children的这个数组里面）
public class MatchList {

    public static List<Comment>  matchChildren(List<Comment> parentComments,List<Comment> childrenComments){

        // 这种普通的 for 双层循环写法：用来遍历List 数组的
        for (int i = 0; i < parentComments.size(); i++) {
            // 外层循环，每一次循环都是找某个 根 评论的其《子》评论

            List<Comment> addchiren = new ArrayList<Comment>();

            for (int j = 0; j < childrenComments.size(); j++) {
                // 内层循环，遍历一整个 《子》数组，看看哪些子评论的  ReplyCommentid 等于 某个根评论的 commentid 。如果等于，就把它就插入对应的 addchiren 数组中，作为某个根 评论的《子》评论
                if(parentComments.get(i).getCommentid()==childrenComments.get(j).getReplyCommentid())
                    addchiren.add(childrenComments.get(j));

            }
            //  最后才把这个addchiren 数组 赋值给 根评论的 children 数组中。
            parentComments.get(i).setChildren(addchiren);

        }

        return parentComments;

    }


    //            错误的写法 ！！！！
//        // 第 1 个匹配数组 父评论
//        List<Comment> parentComments =  commentService.getCommentList(4);
//
//        // 第 2 个匹配数组 子评论
//        List<Comment> childrenComments =  commentService.getCommentList(4);
//
//
//        // 这种 《 for each 》写法用来遍历List数组的
//        for(Comment comment1 : parentComments) {
//            // 双层循环    每一次循环都是找其子评论
//            List<Comment> addchiren = new ArrayList<Comment>();
//            for(Comment comment2 : childrenComments) {
//                // 添加条件就是 ：  ReplyCommnetId等于自己的commentId 那么就添加到children这个List数组里面
//                if(comment1.getCommentid()==comment2.getReplyCommentid())
//                    addchiren.add(comment2);
//
//            }
//            comment1.setChildren(addchiren);
//
//
//        }
//
////      为啥跳出循环后 parentsComments不生效   。因为 for each 是 一个 “”临时“” 变量赋值,没有  影响到原数组的值。并不是  元素本身
//
//            错误的写法 ！！！！



}
