package com.example.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blog.entity.Result;
import com.example.blog.domain.User;
import com.example.blog.service.ArchivesService;



/**
 * 归档模块
 *
 * @author fangjiale 2021年01月26日
 */

@RequestMapping("/archives")
@RestController
public class ArchivesController {

    @Autowired
    private ArchivesService archivesService;



    @GetMapping("/ArchivesList")
    public Result getArchivesList() {
        return new Result(true,"ok", "获取归档信息成功", archivesService.getArchivesList());
    }
}

