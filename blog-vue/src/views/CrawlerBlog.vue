<template>
     <div class="home">
    <div class="blog-banner banner">
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
      <center><h1 class="blog-title animated zoomIn" style="color:white">博客详情</h1></center>
    </div>

    
    <!--中间内容-->
    <div id="waypoint" class="" style="margin-top:50px">
      <div class="ui container">
        <div class="ui top attached segment">
          
          <div class="ui horizontal link list">
           
                          <div class="item">
                            <img :src="dataList.avatar" class="ui avatar image">
                            <div class="content"><a class="header">{{dataList.username}}</a></div>
                          </div>

           
            <div class="item">
              <i class="eye icon"></i> {{dataList.views}}
            </div>
            
          </div>
        </div>
        <div class="ui attached segment">
         
        </div>
        <div class="ui  attached padded segment">
          <!--内容-->
          
          <h2 class="ui center aligned header" v-text="dataList.title"></h2>
          <br>
           





                    <!-- v-html 指令  ： -->






          <div id="content" class="typo  typo-selection js-toc-content m-padded-lr-responsive m-padded-tb-large" v-html="dataList.content" style="width: 800px">
          </div>

          <!--标签-->
         <div class="m-padded-lr-responsive">
<!--            <div class="ui basic teal left pointing label" v-for="item in tagList" :key="item.tagId">{{item.tagName}}</div>-->
              
              
              
             </div>

         





          <div class="ui payQR flowing popup transition hidden">


            <div class="ui orange basic label">
              <div class="ui images" style="font-size: inherit !important;">
                <div class="image">
                  <img src="https://r.photo.store.qq.com/psc?/V53KcXfb1umonn4HbITu3rINxs43TczD/45NBuzDIW489QBoVep5mcUTT*ciAgjJ0cppZCI5w1ILm3Q2J4WJdIQXJXdXVu5HUtU4pM3n8zAHqY3rf6z3B415ulY*M0Dp.HBBJhfDaF*E!/r" alt="" class="ui rounded bordered image" style="width: 120px">
                  <div>支付宝</div>
                </div>
                <div class="image">
                  <img src="https://r.photo.store.qq.com/psc?/V53KcXfb1umonn4HbITu3rINxs43TczD/45NBuzDIW489QBoVep5mcaapv*CZPLor9HYeVrOOiVJnvoxLW18OIo4.CeFhPXXRsV3xEfxMyKMRodIkn6GwaENGRnt8bkvhKT7JrLFzM.w!/r" alt="" class="ui rounded bordered image" style="width: 120px">
                  <div>微信</div>
                </div>
              </div>
            </div>


          </div>



        </div>


        <div class="ui attached positive message">
          <!--博客信息-->
          <div class="ui middle aligned grid">
            <div class="eleven wide column">
              <ui class="list">
                
              </ui>
            </div>
            
          </div>
        </div>







        




      </div>
    </div>

   





    <div class="ui toc-container flowing popup transition hidden" style="width: 250px!important;">
      <ol class="js-toc">
      </ol>
    </div>

    <div id="qrcode" class="ui wechat-qr flowing popup transition hidden " style="width: 130px !important;">
<!--      <img src="../assets/images/wechat.jpg" alt="" class="ui rounded image" style="width: 120px !important;">-->
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

      formData: {
        blogid: '',
        content: '', // 评论内容
        replyCommentid: 0,
        replyUid:0,
        // 做了个判断 ,如果 root 是1,那么就是根评论,那么就是直接插入一条评论.如果是0, 那么它就只是 子评论
        root:1,
        replyUsername:''
      },

      uid: '',
      user: {},
      nickname: '',
      // 被激活的链接地址
      avatar: '',


      dataList: {},


      // 所有的 评论 记录 
      commentsList: [], 

      thumbsFlag: false
    }
  },

   
  created () {

    this.getOneBlog()
    
  },

  methods: {


    

    //    获取某一篇博客的     详细内容
    
     getOneBlog () {

       

       
       
        
         const crawlerblogid = sessionStorage.getItem('crawlerblogid')
          this.$axios.get(`/crawler/${crawlerblogid}`).then(res=>{
          
            
              console.log('oneblog',res.data)
              this.dataList=res.data.data
            
            console.log('abc',this.dataList)

            
            
             
              
              
            })
      
    
    },
 

  





 
  },





  mounted () {
    // 有效
    setTimeout(() => {
      this.reloadPrism()
      // eslint-disable-next-line no-undef
      tocbot.init({
        // Where to render the table of contents.
        tocSelector: '.js-toc',
        // Where to grab the headings to build the table of contents.
        contentSelector: '.js-toc-content',
        // Which headings to grab inside of the contentSelector element.
        headingSelector: 'h1, h2, h3'
      })
    }, 1000)

   
    
  }
}
</script>

<style scoped>
  @import "../assets/css/typo.css";
  @import "../assets/css/animate.css";
  .container{
    animation: main 1s;
  }
  .m-blog {
    padding-top: 69vh !important;
    padding-bottom: 0px !important;
  }
  .blog-banner {
    height: 67vh;
  background: url("../assets/images/bg2.png") center center /  cover no-repeat;
    background-color: #49b1f5;
  }









    /*微信二维码*/
 .weixin{
   position:relative;
 }
.weixin::after{
  content: url(../assets/images/pay.png);
  position: absolute;
  right: 218px;
  top: 50px;
  z-index: 99;
  width:20px;
  height: 20px;
 
  border-radius: 4px;
  transform-origin: top right;
  transform: scale(0);
  opacity: 0;
  -webkit-transition: all .4s ease-in-out;
  -o-transition: all .4s ease-in-out;
  transition: all .4s ease-in-out;
}
.weixin:hover::after{
  transform:scale(1);
  opacity: 1;
}








</style>
