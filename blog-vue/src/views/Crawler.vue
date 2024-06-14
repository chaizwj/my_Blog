<template>


  

  <div id="app" class="home" >
    <!-- banner -->
    <div class="home-banner">
      <div class="banner-container">
        <h1 class="blog-title animated zoomIn">
          爬取的博客
        </h1>
        <!-- 联系方式 -->
        <div class="blog-contact animated zoomIn">
          <a class="github circular icon button" href="https://github.com/chaizwj" data-position="bottom center" style="margin-right: 50px"><i class="github icon"></i></a>
          <a class="wechat circular icon button"  href="https://weibo.com/u/5637624424" style="margin-right: 50px"><i class="weibo icon"></i></a>
          <a class="qq circular icon button" href="https://user.qzone.qq.com/903733284" data-content="1626680964" data-position="bottom center"><i class="qq icon"></i></a>
        </div>
        <div class="ui wechat-qr flowing popup transition hidden">
          <img src="https://r.photo.store.qq.com/psc?/V53KcXfb1umonn4HbITu3rINxs43TczD/45NBuzDIW489QBoVep5mcaapv*CZPLor9HYeVrOOiVLnyRm8OUpwb6xeJ6lITPL.CQBAMN*ufWnqF4BJBqO4o0iDboC.V.GwA1i2AehYs7g!/r" alt="" class="ui rounded image" style="width: 110px">
        </div>
        <!-- 向下滚动 -->
        <div class="scroll-down" @click="scrollDown">
          <h4>向下滚动<i class="el-icon-arrow-down"></i></h4>
        </div>
      </div>
     
      <!--中间内容,如果太窄了可放到container    <div  class="m-container m-padded-tb-big">-->
        
    </div>
    <div  class="m-home" >
      <div class="ui container">

        <div class="ui stackable grid">
            
          <!--左边博客列表-->
          <div class="eleven wide column" id="a">

            <!--header-->
            <div class="ui top attached segment">
              <div class="ui middle aligned two column grid my-shadow">
                <div class="column">
                  <h3 class="ui my-blue header" >从csdn上爬取的博客</h3>
                </div>
                <div class="right aligned column"><h4 class="ui header m-inline-block m-text" v-if="pagination.queryString!=='' && pagination.queryString!==null" style="height: 1px !important;">根据"{{pagination.queryString}}"的搜索结果</h4>
                  共 <h2 class="ui orange header m-inline-block m-text-thin">{{pagination.total}}</h2> 篇
                </div>
              </div>
            </div>

            <!--content-->
            <div class="ui attached  segment my-blog-shadow" >

              <div class="ui padded vertical segment m-padded-tb-large" v-for="item in dataList" :key="item.blogid">
                <div class="ui middle aligned mobile reversed stackable grid">
                 
                 
                 
                  <div class="eleven wide column" style="cursor:pointer;">
                    <h3 class="ui header" @click="toBlog(item.blogid)">{{item.title}}</h3>
                    <div class="ui grid">
                      <div class="eleven wide column">
                        
                        <div class="ui mini horizontal link list">
                         
                          <div class="item">
                            <img :src="item.avatar" class="ui avatar image">
                            <div class="content"><a class="header">{{item.username}}</a></div>
                          </div>
                          <div class="item">
                            <!-- <i class="calendar icon"></i> {{item.createtime}} -->
                          </div>
                          <div class="item">
                            <i class="eye icon"></i> {{item.views}}
                          </div>
                          

                        </div>
                      </div>
         
                    </div>
                  </div>

                                  
                   
                </div>
              </div>
            </div>

            <!--分页   组件-->
            <div class="pagination-container">

                <el-pagination
                        class="pagiantion"

                        @current-change="handleCurrentChange"

                        :current-page="pagination.currentPage"

                        :page-size="pagination.pageSize"

                        layout="total, prev, pager, next, jumper"

                        :total="pagination.total">

                </el-pagination>

            </div>

          </div>


        </div>
       
      </div> 
    </div>
    <br>
    <br>


  </div>

</template>

<script>




export default {
  // 注册组件
  components: { 
    
    
  },
  data () {
    return {
    flag:true,
      input: '', 

      dataList: [], // 当前页  要展示的博客分页列表数据


      typeList: [], // 分类  列表的数据
    
      latestList: [], // 最新发布  的博客列表的数据 前 5 条

      recommendList:[],

      
      pagination: { // 分页相关模型数据
        currentPage: 1, // 当前页码
        pageSize: 5, // 每页显示的记录数
        total: 0, // 总记录数
        queryString: null // 查询条件
      },


      user: {},
      nickname: '',

      // 被激活的链接地址
      avatar: '',
      tip: false,
      
      
      obj: {
        isEnd: false,
        speed: 300,
        singleBack: false,
        sleep: 0,
        type: 'rollback',
        backSpeed: 40,
        sentencePause: true
      },


      articleList: [],
      blogInfo: {},
      current: 1
    
    
    }
  },


  computed:{

  
  },



  created () {

  
    this.findPage()
    this.getUser()
    

  },


  methods: {    

    // 初始化
    scrollDown () {
      window.scrollTo({
        behavior: 'smooth',
        top: document.documentElement.clientHeight
      })
    },


    search () {
      this.findPage()
      this.pagination.queryString = null
    },

  // 点击之后跳转到 某一篇的博客详情

    toBlog (crawlerblogid){
      
      sessionStorage.setItem('crawlerblogid', crawlerblogid)
     
      this.$router.push('/CrawlerBlog')
    },



    // 分页查询
     findPage () {  
     
     this.$axios.get('/crawler/findCrawler/'+this.pagination.currentPage+'/'+this.pagination.pageSize).then(res=>{     

                         console.log('crawler',res.data)
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                         this.dataList = res.data.data.records;           

     })    


     },

    handleCurrentChange (currentPage) {
      // 设置最新的页码
      this.pagination.currentPage = currentPage
      // 重新调用findPage方法进行分页查询
      this.findPage()
    },
    
    handleSizeChange (newSize) {
      this.pagination.pageSize = newSize
      this.findPage()
    }
  },
  
}
</script>

<style scoped>

   #a{
      margin: 0 auto;
   }
   
  .m-home {
    
    padding-top: 105vh ;
    box-sizing: border-box;  
    height: 100%;
  

  }

  .home-banner {
    position: absolute;
    top: 0px;
    left: 0;
    right: 0;
    
    height: 100%;

    background: url("../assets/images/bg2.png") center center /
    cover no-repeat;
    background-color: #49b1f5;
    background-attachment: fixed;
    text-align: center;
    color: #fff !important;
    animation: header-effect 1s !important;
  }
  .banner-container {
    margin-top: 43vh;
    line-height: 1.5;
    color: #eee;
  }
  .blog-contact a {
    color: #fff !important;
  }
  .card-info-social {
    line-height: 40px;
    text-align: center;
    font-size: 1.5rem;
    margin: 6px 0 -6px;
  }
  .left-radius {
    border-radius: 8px 0 0 8px !important;
    order: 0;
  }
  .right-radius {
    border-radius: 0 8px 8px 0 !important;
    order: 1;
  }
  .article-wrapper {
    font-size: 14px;
  }
  @media (min-width: 760px) {
    .blog-title {
      font-size: 2.5rem;
    }
    .blog-intro {
      font-size: 1.5rem;
    }
    .blog-contact {
      line-height: 40px;
      text-align: center;
      font-size: 1.5rem;
      margin: 6px 0 -6px;
    }
    .home-container {
      max-width: 1200px;
      margin: calc(100vh - 50px) auto 0 auto;
      padding: 0 3px;
    }
    .article-card {
      display: flex;
      align-items: center;
      height: 280px;
      width: 100%;
      margin-top: 20px;
    }
    .article-cover {
      overflow: hidden;
      height: 100%;
      width: 45%;
    }
    .on-hover {
      transition: all 0.6s;
    }
    .article-card:hover .on-hover {
      transform: scale(1.1);
    }
    .article-wrapper {
      padding: 0 2.5rem;
      width: 55%;
    }
    .article-wrapper a {
      font-size: 1.5rem;
      transition: all 0.3s;
    }
  }
  @media (max-width: 759px) {
    .blog-title {
      font-size: 24px;
    }
    .blog-contact {
      font-size: 1.25rem;
      line-height: 2;
    }
    .home-container {
      width: 100%;
      margin: calc(100vh - 66px) auto 0 auto;
    }
    .article-card {
      margin-top: 1rem;
    }
    .article-cover {
      border-radius: 8px 8px 0 0 !important;
      height: 230px !important;
      width: 100%;
    }
    .article-cover div {
      border-radius: 8px 8px 0 0 !important;
    }
    .article-wrapper {
      padding: 1.25rem 1.25rem 1.875rem;
    }
    .article-wrapper a {
      font-size: 1.25rem;
      transition: all 0.3s;
    }
  }
  .scroll-down {
    cursor: pointer;
    position: absolute;
    bottom: 0;
    width: 100%;
  }
  .scroll-down i {
    font-size: 2rem;
  }
  .article-wrapper a:hover {
    color: #8e8cd8;
  }
  .article-info {
    font-size: 95%;
    color: #858585;
    line-height: 2;
    margin: 0.375rem 0;
  }
  .article-info a {
    font-size: 95%;
    color: #858585 !important;
  }
  .article-content {
    line-height: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
  }
  .blog-wrapper {
    position: sticky;
    top: 10px;
  }
  .blog-card {
    line-height: 2;
    padding: 1.25rem 1.5rem;
  }
  .author-wrapper {
    text-align: center;
  }
  .blog-info-wrapper {
    display: flex;
    justify-self: center;
    padding: 0.875rem 0;
  }
  .blog-info-data {
    flex: 1;
    text-align: center;
  }
  .blog-info-data a {
    text-decoration: none;
  }
  .collection-btn {
    text-align: center;
    z-index: 1;
    font-size: 14px;
    position: relative;
    display: block;
    background-color: #49b1f5;
    color: #fff !important;
    height: 32px;
    line-height: 32px;
    transition-duration: 1s;
    transition-property: color;
  }
  .collection-btn:before {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: -1;
    background: #ff7242;
    content: "";
    transition-timing-function: ease-out;
    transition-duration: 0.5s;
    transition-property: transform;
    transform: scaleX(0);
    transform-origin: 0 50%;
  }
  .collection-btn:hover:before {
    transition-timing-function: cubic-bezier(0.45, 1.64, 0.47, 0.66);
    transform: scaleX(1);
  }
  .author-avatar {
    transition: all 0.5s;
  }
  .author-avatar:hover {
    transform: rotate(360deg);
  }
  .web-info {
    padding: 0.25rem;
    font-size: 0.875rem;
  }
  .scroll-down-effects {
    color: #eee !important;
    text-align: center;
    text-shadow: 0.1rem 0.1rem 0.2rem rgba(0, 0, 0, 0.15);
    line-height: 1.5;
    display: inline-block;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    animation: scroll-down-effect 1.5s infinite;
  }
  @keyframes scroll-down-effect {
    0% {
      top: 0;
      opacity: 0.4;
      filter: alpha(opacity=40);
    }
    50% {
      top: -16px;
      opacity: 1;
      filter: none;
    }
    100% {
      top: 0;
      opacity: 0.4;
      filter: alpha(opacity=40);
    }
  }
  .big i {
    color: #f00;
    animation: big 0.8s linear infinite;
  }
  @keyframes big {
    0%,
    100% {
      transform: scale(1);
    }
    50% {
      transform: scale(1.2);
    }
  }



   .transition-box {
  
   
   
    background-color: #409EFF;
    
    color: #fff;
   
    box-sizing: border-box;
   
  }

     
   

   




</style>
