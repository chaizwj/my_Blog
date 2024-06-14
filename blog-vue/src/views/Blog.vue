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
    <div id="waypoint"  class="d" style="margin-top:50px">
      <div class="ui container" >
        <div class="ui top attached segment" id="c">
          
          <div class="ui horizontal link list">
           
                          <div class="item">
                            <img :src="dataList.user.avatar" class="ui avatar image">
                            <div class="content"><a class="header">{{dataList.user.username}}</a></div>
                          </div>

            <div class="item">
              <i class="calendar icon"></i> {{dataList.createTime}}
            </div>
            <div class="item">
              <i class="eye icon"></i> {{dataList.views}}
            </div>
            <div class="item">
              <i class="thumbs up outline icon"></i> {{dataList.thumbs}}
            </div>
          </div>
        </div>
        <div class="ui attached segment" id="b">
          <!--图片区域-->
          <img v-bind:src="dataList.firstpicture" class="ui fluid rounded image">
        </div>
        
        <div class="ui  attached padded segment" id="a">
          <!--内容-->
          <div class="ui right aligned basic segment">
            <div class="ui orange basic label">{{dataList.shareStatement}}</div>
          </div>
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
        <div class="ui attached positive message" style="width:850px">
          <!--博客信息-->
          <div class="ui middle aligned grid">
            <div class="eleven wide column">
              <ui class="list">
                
              </ui>
            </div>
            
          </div>
        </div>
            <!--留言区域列表-->
        <div id="comment-container" class="ui bottom attached segment" style="width:850px">
          
          <div class="ui blue segment">
            <div class="ui threaded comments" style="max-width: 100%">
              <h3 class="ui dividing header">Comments</h3>
            
              <div class="comment" v-for="item in commentsList" :key="item.commentid"    >
                

                        <!--    根 评论     -->        
                                <!--   这里必须要用  v-if （和v-show的最大区别：就是会删除dom结构！！
                           ，而v-show的作用：本质就是设置Visible属性，所以dom结构仍存在，只是不显示罢了- --  -》 出现问题 ：会使得每一次增加 子评论的时候 页面很最下面也会增加一个空白行，很怪异！-->
             
                          <!--  只有 root属性为1 的时候，说明它是 根 评论 ，这里只显示根评论。-->
                <div class="content" v-if="item.root==1"> 

                      <a class="avatar">
                  <img v-bind:src="item.user.avatar">
                </a>
                  <a class="author">{{item.user.username}}</a>
                  <div class="metadata">
                    
                  
                    <span class="date">{{item.createTime}}</span>

                  </div>
                  <div class="text" v-text="item.content">
                   
                  </div>

                  <div class="actions" >
                   
                    <a class="reply" @click="replyComment(item)">回复</a>
                     
                  </div>


                </div>
     
   


                       <!--    《子》评论 :  （”折叠“）的 回复评论  显示     -->

                        <!--   这里必须要用  v-if （和v-show的最大区别：就是会删除dom结构！！
                           ，而v-show的作用：本质就是设置Visible属性，所以dom结构仍存在，只是不显示罢了- --  -》 出现问题 ：会使得每一次增加 子评论的时候 页面很最下面也会增加一个空白行，很怪异！-->
             
                                                  <!--  root属性为 0  的时候，说明它是 根 评论 ，这里只显示《子》评论。-->
               <div class="comments"  v-if="item.root==1">
                  <div class="comment" v-for="item2 in item.children" :key="item2.commentid">
                    <a class="avatar">
                      <img :src="item2.user.avatar">
                    </a>
                    <div class="content">
                      <a class="author">{{item2.user.username}}<a class="author" v-if="item2.user.username.length!==0">回复</a><a>{{item2.replyUsername}}:</a></a>
                      <div class="metadata">
                        <span class="date">{{item2.createTime}}</span>
                      </div>
                      <div class="text" v-text="item2.content">
                      </div>
                      <div class="actions" >
                        
                        <!--  回复 《子》评论的话，回复的评论就在 它 的下方。     -->

                        <a class="reply" @click="replyComment(item2)">回复</a>

                      </div>
                    </div>
                    </div>
                </div>








               
              </div>


            </div>
          </div>













          <div class="ui form">
            <el-form ref="addForm" :model="formData">
            <div class="field">


                      <!--  双向数据绑定，输入框 绑定 数据 -->
              <textarea name="content" v-model="formData.content">



              </textarea>


            </div>
            </el-form>
            <div class="fields">
              <div class="field  m-margin-bottom-small m-mobile-wide">

                <br/>
               <button class="ui blue button m-mobile-wide" @click="addComment"><i class="edit icon"></i>发布</button>


              </div>
            </div>

          </div>


        </div>





      </div>
    </div>

    <div id="toolbar" class="m-padded m-fixed m-right-bottom" >
      <div class="ui vertical icon buttons ">
       
        <a href="#comment-container" class="ui blue button" >留言</a>
        
        <!-- 点赞   -->
        <button class="ui icon button" @click="thumbsUp" >
          <i v-if="thumbsFlag" class="thumbs up icon"></i>
          
          <i v-else class="thumbs up outline icon"></i>
         
        </button>

        <!--  收藏 -->
         <button class="ui icon button" @click="save">
         
         <i v-if="savesFlag" class="el-icon-star-on"></i>
          
          <i v-else class="el-icon-star-off"></i>

        </button>


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

      thumbsFlag: false,
      savesFlag: false,
    }
  },

   
  created () {

    this.getOneBlog()
    this.getCommentList()
    this.getUser()

    this.panduan()
  },

  methods: {
    panduan () {
    
    this.$axios.get('/saves/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then((res) => {
  //               如果 后端返回 为非空，所以已经点赞
             if(res.data.data==false)
              {
                this.thumbsFlag = true
              }    
              else{
                this.thumbsFlag = false
              }
         
        })



        this.$axios.get('/thumbs/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then((res) => {
  //               如果 后端返回 为非空，所以已经收藏
             if(res.data.data==false)
              {
                this.savesFlag = true
              }    
              else{
                this.savesFlag = false
              }
         
        })
    
    },

    // 添加评论 ，要先验证是否登入，通过token判断
     addComment () {
    
      if (this.toLogin())  {

        this.formData.blogid = sessionStorage.getItem('blogid')
        // 从session Storage里面获取当前登入用户的信息 ，并且调用  JSON.parse 把字符串转为 js对象 。
        var comment ={}

        comment.uid=JSON.parse(sessionStorage.getItem('user')).uid
        comment.blogid = sessionStorage.getItem("blogid")
        comment.content = this.formData.content
        comment.replyCommentid = this.formData.replyCommentid
        comment.replyUid = this.formData.replyUid
        comment.replyUsername = this.formData.replyUsername
        comment.root = this.formData.root


        if(comment.content=='')
        {
          this.$message.success("不能发表空评论")
        }
        
        else{     


           
             this.$axios.post('/comment',comment).then(() => {

                  console.log(comment.root+'- - -- - - ')

           // 进行过滤不良文字。
            

            this.getCommentList()
           
            this.formData.content = '请输入评论信息...'
            // 弹出提示信息
            this.$message.success("评论成功！")
         
        })
          
          


        }
                                            
       
      }
    },




      // 回复他人评论
    replyComment (item) { 
      // 获取被评论者的uid作为父uid，即 replyUid
      this.formData.content = '对' + item.user.username + '说点啥吧：(回复时，请删除本行)'
      this.formData.replyUid = item.user.uid
      this.formData.replyUsername = item.user.username

      console.log(this.formData.replyUsername+'- - - ')

            // 做了个判断 ,如果 这条评论的 root 属性 是1,那么就是 根 评论,那么就是直接插入并作为它的《子》评论 ；如果是0, 那么它就只是 《子》评论的回复评论，它和《子》评论同属于一个
      if(item.root==1)
        this.formData.replyCommentid = item.commentid        
      else
        this.formData.replyCommentid = item.replyCommentid

     
      this.formData.root = 0;


      
    },


    //    获取某一篇博客的    所有 评论信息 ，所有每个评论的子评论 List<Comment>数据  

     getCommentList () {
     
      const blogid = sessionStorage.getItem('blogid')

                    // 新写法 es6 的 字符串 拼接。
      this.$axios.get(`/comment/${blogid}`).then(res=>{
        
      console.log('comment',res.data.data)
      this.commentsList = res.data.data

      })
    
    },

      //收藏
      save(){


if (this.toLogin()) {
        

    
         this.$axios.get('/saves/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then((res)=>{    
           
              //               如果 后端返回 为非空，所以执行 取消收藏  
             if(res.data.data==false)
             {          
                  this.savesFlag=false
                  this.$confirm('你已经收藏过它了，确定取消收藏', '提示', { // 确认框
          type: 'info'
        }).then(() => {
            
                     //  删除 saves对应的 收藏记录 
                        this.$axios.delete('/saves/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then(()=>{                          
                             
                              
                              this.$message({
                                type: 'info',
                                message: '取消收藏'
                              })  
                })
          
        }).catch(() => {
             
        })
             }
                 //               如果 后端返回 为空，所以执行 收藏 
              else 
              {          
                        this.savesFlag=true
                // 插入到 saves 
                this.$axios.post('/saves/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then(()=>{    
                        this.$message({
                                type: 'success',
                                message: '收藏成功'
                              })  
                   
                })
              }
          })     
     }

      },




      // 点赞  
     thumbsUp () {

      if (this.toLogin()) {
        

        
         this.$axios.get('/thumbs/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then((res)=>{    
           console.log(res)

           console.log(res.data)
           console.log(res.data.data)
              //               如果 后端返回 为非空，所以执行 取消点赞  
             if(res.data.data==false)
             {          
                  
                  this.$confirm('你已经给它点过赞了，是否确定取消点赞', '提示', { // 确认框
          type: 'info'
        }).then(() => {

          this.thumbsFlag = false
 
             //  1 . 更新 blog表 ，进行thumbs 字段 的 -1  
                      this.dataList.thumbs--
              this.$axios.put('/blog/Thumbs/'+this.dataList.blogid+'/'+window.sessionStorage.getItem('uid'),this.dataList).then(()=>{    
              this.$message({
            type: 'info',
            message: '取消点赞'
          })
         
      
           })



                     //  2 . 删除 thumbs 表 对应的 点赞记录 
                        this.$axios.delete('/thumbs/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then(()=>{    
                      
                              console.log('-- - - - ')                    
                })
          
        }).catch(() => {
         
         
        })




              
             

            
             }

           
            

                 //               如果 后端返回 为空，所以执行 点赞   
              else 
              { 
                        this.thumbsFlag = true
                        this.dataList.thumbs++
                        // 更新 对应的blog的thumbs属性 +1
                this.$axios.put('/blog/Thumbs/'+this.dataList.blogid+'/'+window.sessionStorage.getItem('uid'),this.dataList).then(()=>{    
                        this.$message({
                      type: 'success',
                      message: '点赞成功'
                    })
                    
                
                })


                // 插入到 thumbs表 
                this.$axios.post('/thumbs/'+window.sessionStorage.getItem('uid')+'/'+window.sessionStorage.getItem('blogid')).then(()=>{    
                        
                    console.log('123')
                })


              }


          })
      
     }


    },



    //  判断是否登入，不然不能点赞和评论
    toLogin () {
      const tokenStr = window.sessionStorage.getItem('token')
      // 后端指定接口验证了token的正确性
      if (!tokenStr) {

        
        this.$confirm('登录后才能发表评论或者点赞，请问是否先登录？', '提示', { // 确认框
          type: 'info'
        }).then(() => {
          this.$router.push('/login')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '你选择不登录'
          })
          return false
        })

      }
      return !!tokenStr
   
          },

    

    






    //    获取某一篇博客的     详细内容
    
     getOneBlog () {

              

         



              const blogid = sessionStorage.getItem('blogid')
            
            
            this.$axios.get(`/blog/${blogid}`).then(res=>{
          
            
              console.log('oneblog',res.data)
              this.dataList=res.data.data
            
            console.log('abc',this.dataList)

            //  两次 发axios。内部就是 增加一次 views 浏览器次数
            this.dataList.views++
            
              this.$axios.put('/blog/views',this.dataList).then(res=>{
            
              console.log('views',res.data)
              
              

                }
            )
              
              
            })

       
      
    
    },
 

  





    getUser () {
      this.user = window.sessionStorage.getItem('user')
      if (this.user != null) {
        this.uid = JSON.parse(this.user).uid
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



#a{
  width:850px
}

#b{
  width:850px
}


#c{
  width:850px
}

.d{
  margin-left:275px
}
.e{
  margin-left:275px
}
</style>
