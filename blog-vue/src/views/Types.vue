<template>
  <div class="types">    <!-- banner -->
    
    
    
    
    <div class="types-banner banner">
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
                      
      <center><h1 class="blog-title animated zoomIn" style="color:white">分类</h1></center>
       <!-- 联系方式 -->
        <div class="blog-contact animated zoomIn">
              </div>
    </div>



   

    <!--中间内容-->
    <div class="">
      
      <div class="ui container">


        
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        
        
        


        <!--header-->
        <div class="ui top attached segment my-shadow">
          
          <div class="ui middle aligned two column grid">
            <div class="column">
              <h3 class="ui my-blue header">分类</h3>
            </div>
            <div class="right aligned column">
              共 <h2 class="ui orange header m-inline-block m-text-thin"> {{typeList.length}} </h2> 个
            </div>
          </div>
        </div>



<!--   分类  -->
        <div class="ui attached segment m-padded-tb-large my-shadow" >

      

            <div class="ui labeled button m-margin-tb-tiny" v-for="item in typeList" :key="item.typeid">
            
             <a class="ui basic blue button" @click="findPage(item.typeid)">{{item.typename}}</a>

            </div>



        

        </div>





        <!--header-->
        <div class="ui top attached segment my-shadow">
          <div class="ui middle aligned two column grid">
            <div class="column">
              <h3 class="ui my-blue header">博客</h3>
            </div>
            <div class="right aligned column">
              共 <h2 class="ui orange header m-inline-block m-text-thin">{{pagination.total}}</h2> 篇
            </div>
          </div>
        </div>

        <!--content-->
        <div class="ui attached segment my-blog-shadow">

          <div class="ui padded vertical segment m-padded-tb-large" v-for="item in dataList" :key="item.blogid">


            <div class="ui middle aligned mobile reversed stackable grid">
            
            
              <div class="eleven wide column" style="cursor:pointer;">
                <h3 class="ui header" @click="toBlog(item.blogid)">{{item.title}}</h3>
                <p class="m-text" @click="toBlog(item.blogid)">{{item.user.description}}</p>
                <div class="ui grid">
                  <div class="eleven wide column">
                    <div class="ui mini horizontal link list">
                      <div class="item">
                        <img v-bind:src="item.user.avatar" class="ui avatar image">
                        <div class="content"><a class="header">{{item.user.username}}</a></div>
                      </div>
                      <div class="item">
                        <i class="calendar icon"></i> {{item.createTime}}
                      </div>
                      <div class="item">
                        <i class="eye icon"></i> {{item.views}}
                      </div>
                      <div class="item">
                        <i class="thumbs up outline icon"></i> {{item.thumbs}}
                      </div>
                    </div>
                  </div>
                 
                </div>
              </div>

              <div class="five wide column">
                <a target="_blank">
                  <img v-bind:src="item.firstpicture" @click="toBlog(item.blogid)" alt="" class="ui rounded image">
                </a>
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

    <br>
    <br>
  </div>
</template>

<script>
export default {
  data () {
    return {

      
      activeId: '', // 被激活的标签id


      curren_typeid:0,
      dataList: [], // 当前页要展示的博客分页列表数据
      typeList: [], // 分类列表的数据
      tagList: [], // 标签列表的数据
      latestList: [], // 最新发布的博客列表的数据
      pagination: { // 分页相关模型数据
        currentPage: 1, // 当前页码
        pageSize: 6, // 每页显示的记录数
        total: 0, // 总记录数
        queryString: null // 查询条件
      },
      user: {},
      nickname: '',
      // 被激活的链接地址
      avatar: ''
    }
  },
  created () {
    this.getUser()
    this.findPage(this.activeId)
    this.getTypeList()
  },
  methods: {
    toBlog (blogid) {
      sessionStorage.setItem('blogid', blogid)
      this.$router.push('/blog')
    },
    
    async saveNavState (item) {
      this.activeId = item.typeId
      if (item.typeCount === 0) {
        this.$message.error('查询失败，当前所在分类的博客数为0')
      } else {
        await this.findPage(this.activeId)
      }
    },
  
    
     getTypeList () {
     

          this.$axios.get('/home/getType').then(res=>{
              console.log('type',res.data)
             this.typeList = res.data.data 
             
             

     })     

  

    },

    


    // 点击分类按钮的弹出所属于的 博客的分页查询
     findPage (typeid) {
                           
                  let default_typeid=1
                  this.curren_typeid=typeid
                 
                 if(typeid==0)
                  {
                        typeid=default_typeid

                  }
                  
     this.$axios.get('/home/findHomePageByType/'+this.pagination.currentPage+'/'+this.pagination.pageSize+'/'+typeid).then(res=>{
       
                          console.log(res.data)
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                         this.dataList = res.data.data.records;           
              

     })    


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
    },

     handleCurrentChange (currentPage) {
      // 设置最新的页码
      this.pagination.currentPage = currentPage
      // 重新调用findPage方法进行分页查询
      this.findPage(this.curren_typeid)
    },
    
     handleSizeChange (newSize) {
      this.pagination.pageSize = newSize
      this.findPage()
    }


  }
}
</script>

<style scoped>
  .container{
    animation: main 1.0s;
  }
  .m-types {
    padding-top: 69vh !important;
    padding-bottom: 0px !important;
  }
  .types-banner {
    height: 67vh;
  background: url("../assets/images/bg5.png") center center /cover no-repeat;
    background-color: #49b1f5;
  }
  .a{

    margin: 250px auto ;
    color: rgb(255, 255, 255);
  }
</style>
