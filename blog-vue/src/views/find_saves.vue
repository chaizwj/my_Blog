<template>
  <div class="blogs">
    <div class="content-header">
      <h1><small></small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
       
      </el-breadcrumb>
    </div>
    <!--中间内容-->
    <div class="app-container">
    <div class="box">
<!--    <div  class="m-container-small m-padded-tb-big">-->
      <div class="filter-container">
       

  
     
      </div>


      <div class="ui container">


        <el-table size="middle" current-row-key="id" :data="dataList" stripe highlight-current-row>
        
          <el-table-column prop="index" type="index" min-width="15px" align="center" label="序号" >   </el-table-column>
        
          
        
          <el-table-column prop="thumbs1.createtime" label="收藏时间" align="center"></el-table-column>
        
          <el-table-column prop="title" label="收藏的博客" align="center"></el-table-column>

          <el-table-column label="管理" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="warning" @click.native="toBlog(scope.row.blogid)">跳转此博客</el-button>
            
            </template>
          </el-table-column>

        
        
        
        


        </el-table>


        <div class="pagination-container">
          <el-pagination
            class="pagiantion"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.currentPage"
            :page-sizes="[2,3,4,5]"
            :page-size="pagination.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total">
          </el-pagination>
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


  data () {
    return {

      value: '',

    


      pagination: { // 分页相关模型数据
        currentPage: 1, // 当前页码
        pageSize: 5, // 每页显示的记录数
        total: 0, // 总记录数
        queryString: null // 查询条件
      },

    
    
    
    dataList: [], // 当前页要展示的分页列表数据



     
    
    }
  },


  created () {


    this.findPage()
 
   

  },


  methods: {

toBlog (blogid){
      sessionStorage.setItem('blogid', blogid)
     
      this.$router.push('/blog')
    },

   

    



// 分页查询  获取所有 点赞过的   博客
     findPage () {
    
     this.$axios.get('/user/find_saves/'+this.pagination.currentPage+'/'+this.pagination.pageSize+'/'+window.sessionStorage.getItem('uid')).then(res=>{
       
                         
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                       


                           
                          this.dataList = res.data.data.records;    
                          console.log(this.dataList)
                          
                       
                       

                          
     })    


     },









    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
      // 刷新页面，删除vuex数据
      window.location.reload()
    },


    // 切换页码
    handleCurrentChange (currentPage) {
      // 设置最新的页码
      this.pagination.currentPage = currentPage
      // 重新调用findPage方法进行分页查询
      this.findPage()
    },



    handleSizeChange (newSize) {
      this.pagination.pageSize = newSize
      this.findPage()
    },



    // 重置表单
    resetForm () {
      this.formData = {}// 重置表格数据
      this.$refs.dataAddForm.resetFields()
    },


    // 弹出添加窗口
    handleCreate () {
      this.dialogFormVisible = true
      this.resetForm()
    },



    
    handleUpdate (blogid) {
      
      sessionStorage.setItem('admin_blogid',blogid)
      this.$router.push('/AdminHome/Postblogs')
     
    }

  }



}
</script>

<style lang="less" scoped>
  @import "../assets/css/style.css";
  @import "../assets/css/me.css";
  .blogs {
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>
