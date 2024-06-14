<template>
  <div class="about">



    <div class="about-banner banner">
    
 <center><h1 class="banner-title"></h1></center>
       <center><h1 class="banner-title"></h1></center> 
       <center><h1 class="banner-title"></h1></center>
       <center><h1 class="banner-title"></h1></center>
        <center><h1 class="banner-title"></h1></center>
         <center><h1 class="banner-title"></h1></center>
          <center><h1 class="banner-title"></h1></center>
           <center><h1 class="banner-title"></h1></center>
            <center><h1 class="banner-title"></h1></center>
             <center><h1 class="banner-title"></h1></center>
              <center><h1 class="banner-title"></h1></center>
               <center><h1 class="banner-title"></h1></center>
                <center><h1 class="banner-title"></h1></center>
                 <center><h1 class="banner-title"></h1></center>
                  <center><h1 class="banner-title"></h1></center>
                   <center><h1 class="banner-title"></h1></center>
                    <center><h1 class="banner-title"></h1></center>
                     <center><h1 class="banner-title"></h1></center>
                      <center><h1 class="banner-title"></h1></center>
      <center><h1 class="blog-title animated zoomIn" style="color:white">个人中心</h1></center>


      


    </div>


         

    <!--中间内容-->
    <div   style="margin-top:50px;margin-bottom:100px;">
      <div class="ui container">

        <div class="ui stackable grid">

          
          <div class="eleven wide column" style="margin-top:8px; height:500px; ">

        


            <el-card class="box-card">


            <!-- 指定组件的呈现位置 -->           
         <router-view  ></router-view>



            </el-card>
           






            
          </div>


          <div class="five wide column" >
            
            
            <div class="ui top attached segment my-shadow" style="margin-top:8px">
              <p class="m-text" @click="PostBlogs">发布博客</p>
            </div>


            <div class="ui attached segment my-shadow" style="margin-top:40px">
              <p class="m-text"  @click="personal">个人中心</p>
            </div>


          

           
            <div class="ui attached segment my-shadow" style="margin-top:40px">
               <p class="m-text" @click="find_saves">查看收藏过的博客</p>
            </div>


            <div class="ui attached segment my-shadow" style="margin-top:40px">
               <p class="m-text" @click="find_thumbs">查看点赞过的博客</p>
            </div>
            
            <div class="ui attached segment my-shadow" style="margin-top:40px">
               <p class="m-text" @click="getMYBlogs">查看发过的博客</p>
            </div>

        
            



          </div>
        </div>

      </div>
    </div>

    <div id="toolbar" class="m-padded m-fixed m-right-bottom" style="display: none">
      <div class="ui vertical icon buttons ">
        <button type="button" class="ui toc my-blue button" >目录</button>
        <a href="#comment-container" class="ui my-blue button" >留言</a>
        <button class="ui wechat icon button"><i class="weixin icon"></i></button>
        <div id="toTop-button" class="ui icon button" ><i class="chevron up icon"></i></div>
      </div>
    </div>

    <div class="ui toc-container flowing popup transition hidden" style="width: 250px!important;">
      <ol class="js-toc">

      </ol>
    </div>

    <div id="qrcode" class="ui wechat-qr flowing popup transition hidden " style="width: 130px !important;">
      <!--<img src="./static/images/wechat.jpg" alt="" class="ui rounded image" style="width: 120px !important;">-->
    </div>
    <br>
    <br>
  </div>

</template>

<script>

export default {
  data () {
    return {
      user: {},
      nickname: '',
      // 被激活的链接地址
      avatar: '',
      
    
  
    
    }
  },
  
  
    
  created () {
    this.getUser()
  },


  methods: {

    find_saves(){
       this.$router.push('/About/find_saves')
    },
    getMYBlogs(){
       this.$router.push('/About/find_blogs')
    },
    find_thumbs(){
      this.$router.push('/About/find_thumbs')
    },



    PostBlogs(){
     this.$axios.get('/user/'+sessionStorage.getItem('uid')).then(
         
                    
              res=>{         
                    //  只有是博主才能发布博客。      
                    if(res.data.data.blogger==1)    
                      
                      {


                             // 编程式路由
                          this.$router.push('/About/PostBlogs')
                        
                        

                      }

                else

                {
                 
                 
                  this.$message.error('你还不是博主，还不能发博客！')


                }
                  
                  })  
    },



        personal(){

          


      this.$router.push('/About/personal')

          


        },
    
    getUser () {
      this.user = window.sessionStorage.getItem('user')
      if (this.user != null) {
        this.nickname = JSON.parse(this.user).nickname
        this.avatar = JSON.parse(this.user).avatar
      }
    },

    logout () {
      window.sessionStorage.clear()
      this.$router.push('/home')
      // 刷新页面，删除vuex数据
      window.location.reload()
    }
  }
}
</script>

<style scoped>
 .about-banner {
   height: 400px;
  background: url("../assets/images/bg4.png") center center /
  cover no-repeat;
   background-color: #49b1f5;}
 .container{
   animation: main 1.0s;
 }
 .m-about {
   padding-top: 450px !important;
   padding-bottom: 0 !important;
 }




.el-carousel__item  {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 800px;
    
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
     
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
    
  }




</style>
