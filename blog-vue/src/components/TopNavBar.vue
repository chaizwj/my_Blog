<template>
  <div id="navClass" class="navClass" hide-on-scroll flat height="58">
    <!-- 电脑导航栏 -->
    <div class="d-md-block d-none nav-container">
      <div class="float-left blog-title">
<!--        <router-link to="/">-->
<!--        </router-link>-->
      </div>
      <div class="float-right nav-title">
        <el-input v-model="queryString" v-if="searchFlag" style="float: top;width: 100px" placeholder="请输入内容"></el-input><i class="el-icon-circle-close" v-if="searchFlag" @click="closeSearch"></i>
      
      
        


        <div class="menus-item">


          <router-link to="/home" >
            <i class="el-icon-house" style="color: whitesmoke" /><span style="color: whitesmoke;margin-right: 10px"  >首页</span>
          </router-link>


        </div>


        <div class="menus-item">



          <router-link to="/Message" >
            <i class="el-icon-paperclip" style="color: whitesmoke" /><span style="color: whitesmoke;margin-right: 10px"  >留言板</span>
          </router-link>


        </div>



    
        <div class="menus-item">

          <router-link to="/types">
            <i class="el-icon-s-operation" style="color: whitesmoke"/><span style="color: whitesmoke;margin-right: 10px" >分类</span>
          </router-link>
          
        </div>


  


        <div class="menus-item">
          <router-link to="/Crawler">
            <i class="el-icon-setting" style="color: whitesmoke"/><span style="color: whitesmoke;margin-right: 10px">爬虫</span>
          </router-link>
        </div>



     


      


        <div class="user-btn">
          <!--  这里 v-if 和 v-else 相配合 -->
          <template v-if="avatar===''">
         <div class="menus-item">
          <router-link to="/login">
            <i class="el-icon-s-custom" style="color: whitesmoke"/> <span style="color: whitesmoke;margin-right: 10px">登入</span>
          </router-link>
        </div>
          </template>

<!--       v-else 和 v-if 配合使用。v-else套在template里面  这个是 就是   登入之后  的要变成的东西   -->
          <template v-else>
            <img
              class="user-avatar"
              v-bind:src=this.avatar
              height="30"
              width="30"
            />  
            <ul class="user-submenu">

            
              <li>
                <router-link to="/About">
                  <i class="user circle icon"/> 个人中心
                </router-link>
              </li>
             




              <li>
                <a @click="logout"><i class="sign out alternate icon" />退出</a>
              </li>
            </ul>
          </template>


        </div> 
      </div>
    </div>

    
   
  </div>


</template>

<script>

export default {
  components: {
 
  },
  created () {
    this.getUser()


    
  },
  mounted () {
    window.addEventListener('scroll', this.scroll)
  },
  // data: function () {
  //   return {
  //
  //   }
  // },
  data () {
    
    return {
      navClass: '',
      avatar: '',
      user: {},
      queryString: '',
      searchFlag: false,
      username:''

    }
  },
  methods: {

 

      



    
    toLogin () {
      const tokenStr = window.sessionStorage.getItem('token')
      // 后端指定接口验证了token的正确性
      if (!tokenStr) {
        this.$confirm('登录后才能开启聊天室，请问是否先登录？', '提示', { // 确认框
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
    openRoom () {
      if (this.toLogin()) { this.$refs.room.openFrame() }
    },
    closeSearch () {
      this.searchFlag = false
    },
    search () {
      this.$refs.searchModel.setDialogVisible()
      // this.searchFlag = true
      // if (this.queryString !== '') {
      //   sessionStorage.setItem('queryString', this.queryString)
      //   this.queryString = ''
      //   this.searchFlag = false
      //   if (this.$route.path === '/home') { window.location.reload() } else {
      //     this.$router.push('/home')
      //   }
      // }
    },
    scroll () {
      const that = this
      const scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop
      that.scrollTop = scrollTop
      if (that.scrollTop > 60) {
        that.navClass = 'nav-fixed'
      } else {
        that.navClass = 'nav'
      }
    },
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/home')
      // 刷新页面，删除vuex数据
      window.location.reload()
    },


  




//先获取当前用户的个人信息 
    getUser () {


       this.$axios.get('/user/'+sessionStorage.getItem('uid')).then((res) => {
           this.username = res.data.data.username
        this.avatar = res.data.data.avatar
           
          })



    }

  },


  computed: {
    // avatar () {
    //   return this.avatar
    // }
  }
}




</script>

<style scoped>
  .navClass{
    /*position:fixed; !* 绝对定位，fixed是相对于浏览器窗口定位。 *!*/
    position: relative;
    top:15px; /* 距离窗口顶部距离 */
    right: 22px;
    height:10px; /* 高度 */
    float: right;
    z-index:99; /* 层叠顺序，数值越大就越高。页面滚动的时候就不会被其他内容所遮挡。 */
  }
  i {
    margin-right: 4px;
  }
  ul {
    list-style: none;
  }
  .nav {
    background: rgba(0, 0, 0, 0) !important;
  }
  .nav a {
    color: #eee !important;
  }
  .nav .menus-item a {
    text-shadow: 0.05rem 0.05rem 0.1rem rgba(0, 0, 0, 0.3);
  }
  .nav .blog-title a {
    text-shadow: 0.1rem 0.1rem 0.2rem rgba(0, 0, 0, 0.15);
  }
  .theme--light.nav-fixed {
    background: rgba(255, 255, 255, 0.8) !important;
    box-shadow: 0 5px 6px -5px rgba(133, 133, 133, 0.6);
  }
  .theme--dark.nav-fixed {
    background: rgba(18, 18, 18, 0.8) !important;
  }
  .theme--dark.nav-fixed a {
    color: rgba(255, 255, 255, 0.8) !important;
  }
  .theme--light.nav-fixed a {
    color: #4c4948 !important;
  }
  .nav-fixed .menus-item a,
  .nav-fixed .menus-btn a,
  .nav-fixed .blog-title a {
    text-shadow: none;
  }
  .nav-container {
    font-size: 14px;
    width: 100%;
    height: 100%;
  }
  .nav-mobile-container {
    width: 100%;
    display: flex;
    align-items: center;
  }
  .blog-title,
  .nav-title {
    display: flex;
    align-items: center;
    height: 100%;
  }
  .blog-title a {
    font-size: 18px;
    font-weight: bold;
  }
  .user-btn,
  .menus-btn,
  .menus-item {
    position: relative;
    display: inline-block;
    margin: 0 0 0 0.875rem;
  }
  .menus-btn a,
  .menus-item a {
    transition: all 0.2s;
  }
  .nav-fixed .menus-btn a:hover,
  .nav-fixed .menus-item a:hover {
    color: #49b1f5 !important;
  }
  .menus-item a:hover:after {
    width: 100%;
  }
  .menus-item a:after {
    position: absolute;
    bottom: -5px;
    left: 0;
    z-index: -1;
    width: 0;
    height: 3px;
    background-color: #80c8f8;
    content: "";
    transition: all 0.3s ease-in-out;
  }
  .user-btn a {
    transition: all 0.2s;
  }
  .user-avatar {
    cursor: pointer;
    border-radius: 50%;
  }
  .user-btn:hover .user-submenu {
    display: block;
  }

  .user-submenu {
    position: absolute;
    display: none;
    right: 0;
    width: max-content;
    margin-top: 8px;
    box-shadow: 0 5px 20px -4px rgba(0, 0, 0, 0.5);
    background-color: #fff;
    animation: submenu 0.3s 0.1s ease both;
  }
  
  .user-submenu:before {
    position: absolute;
    top: -8px;
    left: 0;
    width: 100%;
    height: 20px;
    content: "";
  }
  .user-submenu a {
    line-height: 2;
    color: #4c4948 !important;
    text-shadow: none;
    display: block;
    padding: 6px 14px;
  }
  .user-submenu a:hover {
    background: #4ab1f4;
  }
  @keyframes submenu {
    0% {
      opacity: 0;
      filter: alpha(opacity=0);
      transform: translateY(10px);
    }
    100% {
      opacity: 1;
      filter: none;
      transform: translateY(0);
    }
  }
</style>
