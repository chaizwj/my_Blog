package com.example.blog.controller;


import com.example.blog.domain.Type;
import com.example.blog.entity.Result;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController  {

    @Autowired
    private TypeService typeService;



    //增加  博客的类别
    @PostMapping
    public Result addType(@RequestBody Type type){


        typeService.addType(type);
        return new Result(true,"ok", "添加一个新分类成功");
    }

    //修改 博客的类别
    @PutMapping
    public Result updateType(@RequestBody Type type){

        System.out.println(type.getTypeid());
        typeService.updateType(type);
        return new Result(true,"ok", "修改一个分类成功");
    }


    //删除 博客的类别
    @DeleteMapping("{typeid}")
    public Result deleteType(@PathVariable int typeid){


        typeService.deleteType(typeid);
        return new Result(true,"ok", "删除一个新分类成功");
    }
    
    


}
