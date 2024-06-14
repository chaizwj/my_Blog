package com.example.blog.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.domain.Blog;
import com.example.blog.domain.CrawledBlog;
import com.example.blog.entity.Result;
import com.example.blog.service.BlogService;
import com.example.blog.service.CrawlerBlogServicce;
import com.example.blog.service.TypeService;
import com.example.blog.util.DbPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/home")
@RestController

public class HomeController {




    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;




    //   主页展示时候的blog分页
        @GetMapping("/findHomePage/{currentPage}/{pageSize}")
        public Result findHomePage(@PathVariable int currentPage,@PathVariable int pageSize) {

            IPage<Blog> page = blogService.findHomePage(currentPage, pageSize);
            return new Result(true,"ok", "获取分页数据成功", page);
        }


//   主页的 分类   分页+带 typeid <条件查询>根据 typeid 展示对应分类的博客 带上user

    @GetMapping("/findHomePageByType/{currentPage}/{pageSize}/{typeid}")
    public Result findHomePageByType(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable int typeid){

        IPage<Blog> page = blogService.findHomePageByType_With_user(currentPage, pageSize,typeid);
        return new Result(true,"ok", "获取分页数据成功",page);

    }




    //   admin的主页的 分类时候展示，带 typeid 条件查询根据 typeid 展示对应分类的博客 带上type

    @GetMapping("/findAdmin_Blogs_ByType/{currentPage}/{pageSize}/{typeid}")
    public Result findAdmin_Blogs_ByType(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable int typeid){

        IPage<Blog> page = blogService.getAdmin_BlogBy_Type_Id_With_Nothing(currentPage, pageSize,typeid);
        return new Result(true,"ok", "获取分页数据成功",page);

    }



        // 根据 title 搜索一个 博文
    @GetMapping("/findAdmin_Blogs_ByTitle/{currentPage}/{pageSize}/{title}")
    public Result findAdmin_Blogs_ByTitle(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String title){

         System.out.println(title);
        IPage<Blog> page = blogService.getAdmin_BlogBy_Title_With_Nothing(currentPage, pageSize,title);
        return new Result(true,"ok", "获取分页数据成功",page);

    }





//获取  博客的类别
    @GetMapping("/getType")
    public Result getType(){

        return new Result(true,"ok", "获取分类信息成功", typeService.getType());
    }






    @GetMapping("/latestList")
    public Result getRecommendList() {
        return new Result(true,"ok", "获取最新推荐信息成功",blogService.getLatestList());
    }



    //   主页展示时候   显示最前的前十条 博文 ,本剧 发表时间排序 然后 limit 10
    @GetMapping("/getRank")
    public Result findLatestBlogs() {


        return new Result(true,"ok", "获取最新博文前十条成功",blogService.getRank());
    }


    //   主页展示时候   推荐博文
    @GetMapping("/getrecommendList")
    public Result getrecommendList() {


        return new Result(true,"ok", "获取 推荐博文 ",blogService.getrecommendList());
    }



}
