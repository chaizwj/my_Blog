package com.example.blog.service;





import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.domain.Blog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
public interface BlogService extends IService<Blog> {

/*
  主页查询的分页
 */
     IPage<Blog> findHomePage(int currentPage, int pageSize);

/*
 分类查询的分页 根据 typeid 查询 对应博客 ,并且多表查询 带上 user
 */
     IPage<Blog> findHomePageByType_With_user(int currentPage, int pageSize,int typeid);



      /*
  // admin 主页的带分页的 带条件的:根据typeid的 查询 blog +type
 */

    public IPage<Blog> getAdmin_BlogBy_Type_Id_With_Nothing(int currentPage, int pageSize, int typeid);





     /**
      * 根据博客id获取博客   ,同时多表查询  并且多表查询 user表
      * @param blogid
      * @return blog
      */
    Blog getOneBlog(int blogid);





//    增加 博客浏览次数

    void addViews(Blog blog);


    void addThumbs(Blog blog);

    void addBlogs(Blog blog);



    /**
     * 按照取最新推荐的博客列表  recommend 的值 为1 就是要选出来
     * @return list
     */
    List<Blog> getLatestList();



//    admin 博主管理页面的。带分页的  多表查询 blog+type。就是为了得到type中对应的typeName

    public IPage<Blog> getBlogs_with_typeName(int currentPage, int pageSize);

//  admin 博主管理页面的,获取  单 个 博客的具体内容 ,包括  type 多表查询

    public Blog get_admin_One_Blog_with_typeName(int blogid);



    void UpdateBlogs(Blog blog);

    void DeleteBlogById(Integer blogid);

    List<Blog> getRank();

    List<Blog> getrecommendList();

    IPage<Blog> getAdmin_BlogBy_Title_With_Nothing(int currentPage, int pageSize, String title);

    int insertthumbs_blogid_uid(int blogid, int uid);
}

