package com.example.blog.service.impl;


import com.example.blog.dao.BlogDao;
import com.example.blog.dao.CommentDao;
import com.example.blog.dao.UserDao;
import com.example.blog.domain.Blog;
import com.example.blog.service.EChartService;
import com.example.blog.util.DataUtil;
import com.example.blog.vo.BarVO;
import com.example.blog.vo.DataVO;
import com.example.blog.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EChartServiceImpl  implements EChartService {
    @Autowired
    BlogDao blogDao;

    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;


    // 柱状图 。
    public BarVO barVOListView() {

        BarVO barVO = new BarVO();
        List<String> names = new ArrayList<>();
        List<DataVO> values = new ArrayList<>();
        //先查出数据
        List<Blog> blogs = this.blogDao.barVOListView();
        for (Blog blog : blogs) {
            names.add(blog.getTitle());
            DataVO dataVO = new DataVO();
            dataVO.setValue(blog.getViews());
            dataVO.setItemStyle(DataUtil.createItemStyle(blog.getViews()));
            values.add(dataVO);
        }
        barVO.setNames(names);
        barVO.setValues(values);
        //转换VO
        return barVO;

    }


    @Override
    public Object barVOListThumb() {

        BarVO barVO = new BarVO();
        List<String> names = new ArrayList<>();
        List<DataVO> values = new ArrayList<>();
        //先查出数据
        List<Blog> blogs = this.blogDao.barVOListThumb();
        for (Blog blog : blogs) {
            names.add(blog.getTitle());
            DataVO dataVO = new DataVO();
            dataVO.setValue(blog.getThumbs());
            dataVO.setItemStyle(DataUtil.createItemStyle(blog.getThumbs()));
            values.add(dataVO);
        }
        barVO.setNames(names);
        barVO.setValues(values);
        //转换VO
        return barVO;

    }


    // 饼图
    @Override
    public Object sumPie() {

        List<PieVO> pieVOList = new ArrayList<>();

        PieVO pieVO1 = new PieVO();
        pieVO1.setName("浏览总数");
        pieVO1.setValue(blogDao.sumViewsPie());


        PieVO pieVO2 = new PieVO();
        pieVO2.setName("点赞总数");
        pieVO2.setValue(blogDao.sumThumbsPie());

        PieVO pieVO3 = new PieVO();
        pieVO3.setName("用户总数");
        pieVO3.setValue(userDao.countUsersPie());

        PieVO pieVO4 = new PieVO();
        pieVO4.setName("评论总数");
        pieVO4.setValue(commentDao.sumComenntsPie());

        pieVOList.add(pieVO1);
        pieVOList.add(pieVO2);
        pieVOList.add(pieVO3);
        pieVOList.add(pieVO4);

        return pieVOList;

    }

}
