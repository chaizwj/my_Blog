package com.example.blog.controller;


import com.example.blog.entity.Result;
import com.example.blog.service.EChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/echart")
@RestController
public class EChartController {

    @Autowired
    private EChartService eChartService;


    @GetMapping("/barVOListView")
    private Result barVOListView(){

        return new Result(true,"ok", "获取博客信息成功",eChartService.barVOListView());

    }

    @GetMapping("/barVOListThumb")
    private Result barVOListThumb(){

        return new Result(true,"ok", "获取博客信息成功",eChartService.barVOListThumb());

    }


    @GetMapping("/sumPie")
    private Result sumPie(){

        return new Result(true,"ok", "获取博客信息成功",eChartService.sumPie());

    }

}
