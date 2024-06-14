package com.example.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.dao.BlogDao;
import com.example.blog.domain.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 博客服务实现类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */


@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {

    @Autowired
    private BlogDao blogDao;
    private Integer currentPage;
    private Integer pageSize;
    private Integer start;



    /*
      主页查询的分页
     */
    @Override
    public IPage<Blog> findHomePage(int currentPage, int pageSize) {
        IPage<Blog> page = new Page(currentPage,pageSize);
        page = blogDao.getBlogByIdWith_OneUser(page);
        return page;
    }


    /*
  分类查询的分页 根据 typeid 查询 对应博客 ,并且多表查询 带上 user
 */

    @Override
    public IPage<Blog> findHomePageByType_With_user(int currentPage, int pageSize,int typeid) {

        IPage<Blog> page = new Page(currentPage,pageSize);
        page = blogDao.getBlogBy_Type_Id_With_OneUser(page,typeid);
        return page;

    }


     /*
  // admin 主页的带分页的 带条件的:根据typeid的 查询 blog +type
 */

    @Override
    public IPage<Blog> getAdmin_BlogBy_Type_Id_With_Nothing(int currentPage, int pageSize, int typeid) {

        IPage<Blog> page = new Page(currentPage,pageSize);
        page = blogDao.getAdmin_BlogBy_Type_Id_With_Nothing(page,typeid);
        return page;
    }


    /**
     * 根据博客id获取博客  并且多表查询 user表
     * @param blogid
     * @return blog
     */
    public Blog getOneBlog(int blogid) {

        Blog blog = blogDao.getOne_BlogByIdWith_One_User(blogid);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }



    /**
     * 增加  博客  浏览次数
     * @param blog
     * @return blog
     */
    public void addViews(Blog blog) {

        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("blogid", blog.getBlogid());
        blogDao.update(blog,qw);

    }

    /**
     * 增加  博客  点赞数
     * @param blog
     * @return blog
     */
    @Override
    public void addThumbs(Blog blog) {

        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("blogid", blog.getBlogid());
        blogDao.update(blog,qw);

    }

    /**
     * 添加博客
     * @param blog
     * @return blog
     */

    @Override
    public void addBlogs(Blog blog) {

        blogDao.insert(blog);

    }


    public List<Blog> getLatestList() {

        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("recommend",1);
        return blogDao.selectList(qw);

    }






    /*
      admin 博主管理页面的。带分页的  多表查询 blog+type。就是为了得到type中对应的typeName
     */
    @Override
    public Blog get_admin_One_Blog_with_typeName(int blogid) {

        return    blogDao.get_admin_One_Blog_with_typeName(blogid);

    }





    /*
   //  admin 博主管理页面的,获取  单 个 博客的具体内容 ,包括  type类型,多表查询
     */
    @Override
    public IPage<Blog> getBlogs_with_typeName(int currentPage, int pageSize) {

        IPage<Blog> page = new Page(currentPage,pageSize);
        page = blogDao.getBlogs_with_typeName(page);
        return page;
    }




    @Override
    public void UpdateBlogs(Blog blog) {
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("blogid", blog.getBlogid());
        blogDao.update(blog,qw);

    }


    @Override
    public void DeleteBlogById(Integer blogid) {
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("blogid", blogid);
        blogDao.delete(qw);
    }



// 获取最新的博文
    @Override
    public List<Blog> getRank() {

        return blogDao.getRank();
    }


    // 获取 推荐 博文  recommend =1 的博文
    @Override
    public List<Blog> getrecommendList() {
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("recommend",1);
        return blogDao.selectList(qw)  ;
    }

    @Override
    public IPage<Blog> getAdmin_BlogBy_Title_With_Nothing(int currentPage, int pageSize, String title) {

        IPage<Blog> page = new Page(currentPage,pageSize);
        page = blogDao.getAdmin_BlogBy_Title_With_Nothing(page,title);
        return page;


    }


    @Override
    public int insertthumbs_blogid_uid(int blogid, int uid) {
        return blogDao.insertthumbs_blogid_uid(blogid,uid);
    }


}
