package com.example.blog.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.domain.*;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
@Mapper
public interface BlogDao extends BaseMapper<Blog> {

    @Select("SELECT * FROM blog WHERE blogid = #{blogid}")
    Blog getById(int blogid);

    //用于 《归档》 的 不带分页 不带条件的 多表查询 user+blog  ,按时间降序排序
    @Results({@Result(column = "uid",property = "uid"),
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))
    })
    @Select("SELECT * FROM blog order by createTime desc")
    List<Blog> For_Archives_get_Blogs_with_user();


    //用于 后台数据分析 ,   按 浏览数 降序排序
    @Select("SELECT * FROM blog order by views desc limit 6")
    List<Blog> barVOListView();


//用于后台数据分析 ,  按 点赞数   降序排序
    @Select("SELECT * FROM blog order by thumbs desc limit 6")
    List<Blog> barVOListThumb();

//带分页的 不带条件的 多表查询 user+blog
    @Results({@Result(column = "uid",property = "uid"),
      @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))
    })
    @Select("SELECT * FROM blog ")
    IPage<Blog> getBlogByIdWith_OneUser(IPage<Blog> pagegation);




    //带分页的 带条件的:根据typeid的 多表查询 user+blog
    @Results({@Result(column = "uid",property = "uid"),
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))
    })
    @Select("SELECT * FROM blog  where typeid =#{typeid}")
    IPage<Blog> getBlogBy_Type_Id_With_OneUser(IPage<Blog> pagegation,int typeid);



    // admin 主页的带分页的 带条件的:根据typeid的 查询 blog+type
    @Results({@Result(column = "typeid",property = "typeid"),
            @Result(column = "typeid", property = "type",one = @One(select = "com.example.blog.dao.TypeDao.getById"))
    })
    @Select("SELECT * FROM blog  where typeid =#{typeid}")
    IPage<Blog> getAdmin_BlogBy_Type_Id_With_Nothing(IPage<Blog> pagegation,int typeid);







    //带分页的  多表查询 blog+type。就是为了得到type中对应的typeName
    @Results({@Result(column = "typeid",property = "typeid"),
            @Result(column = "typeid", property = "type",one = @One(select = "com.example.blog.dao.TypeDao.getById"))
    })
    @Select("SELECT * FROM blog")
    IPage<Blog> getBlogs_with_typeName(IPage<Blog> pagegation);





// 不带分页的 带条件的 :blogid  多表查询的 user+blog

    @Results({@Result(column = "uid",property = "uid"),
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))
    })
    @Select("SELECT * FROM blog where blogid=#{blogid}")
    Blog getOne_BlogByIdWith_One_User(int blogid);







    // 不带分页的 多表查询的 type+blog  查询单个博客 的 +type

    @Results({
            @Result(column = "typeid",property = "typeid"),
            @Result(column = "typeid", property = "type",one = @One(select = "com.example.blog.dao.TypeDao.getById"))
    })
    @Select("SELECT * FROM blog where blogid=#{blogid}")
    Blog get_admin_One_Blog_with_typeName(int blogid);




    //   主页展示时候   显示最前的前十条 博文 ,本剧 发表时间排序 然后 limit 10

    @Select("select * from blog order by thumbs desc limit 10")
    List<Blog> getRank();





        //带分页的 带条件的:根据title的 多表查询 user+blog
    @Results({@Result(column = "uid",property = "uid"),
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById"))
    })

    // 这里的模糊查询写法比较特殊,要这么写才行    concat('%',#{  },'%')
    @Select("SELECT * FROM blog  where title like concat('%',#{title},'%')")
    IPage<Blog> getAdmin_BlogBy_Title_With_Nothing(IPage<Blog> page, String title);


    // 插入一条thumbs 记录
    @Insert("insert into thumbs(blogid,uid) values(#{blogid},#{uid})")
    int insertthumbs_blogid_uid(int blogid, int uid);




    //带分页的 多表查询 user+blog+type
    @Results({@Result(column = "uid",property = "uid"),
            @Result(column = "uid", property = "user",one = @One(select = "com.example.blog.dao.UserDao.getById")),
            @Result(column = "typeid", property = "type",one = @One(select = "com.example.blog.dao.TypeDao.getById"))

    })
    @Select("SELECT * FROM blog  where uid=#{uid}")
    IPage<Blog> find_blogs(IPage<Blog> page, int uid);




    //  饼图 统计总数
    @Select("SELECT sum(views) FROM blog")
    int sumViewsPie();


    @Select("SELECT sum(thumbs) FROM blog")
    int sumThumbsPie();




}




