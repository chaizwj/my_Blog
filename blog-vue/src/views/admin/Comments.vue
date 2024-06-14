<template>
  <div class="blogs">
    <div class="content-header">
      <h1>评论管理<small></small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item >后台管理</el-breadcrumb-item>
        <el-breadcrumb-item>评论管理</el-breadcrumb-item>
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
          <!--                        id,用户名，真实姓名，角色，备注，最后登录时间，创建时间-->
          <el-table-column prop="index" type="index" min-width="15px" align="center" label="序号"></el-table-column>
        
          <el-table-column prop="content" label="评论内容" align="center"></el-table-column>
        
          <el-table-column prop="createTime" label="评论时间" align="center"></el-table-column>
        
          <el-table-column prop="blog.title" label="评论的博客标题" align="center"></el-table-column>

          <el-table-column prop="user.username" label="评论的用户名称" align="center"></el-table-column>
        
        
          <el-table-column label="管理" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click.native="handleDelete(scope.row.commentid)">删除</el-button>
              
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
        pageSize: 10, // 每页显示的记录数
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



   

    



// 分页查询  获取所有评论
     findPage () {
    
     this.$axios.get('/comment/getAllCommentList/'+this.pagination.currentPage+'/'+this.pagination.pageSize).then(res=>{
       
                         
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                       
                       
                          this.dataList = res.data.data.records;    
                           console.log('admin_comments',res.data.data.records)
                       
                       

                          
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


//            删除评论 

    handleDelete (commentid) {
     
     this.$confirm("此操作永久删除当前信息，是否继续？","提示",{type:"info"}).then(()=>{
           
                    this.$axios.delete('/comment/'+commentid).then((res)=>{


                            if(res.data.flag)
                            this.$message.info("删除成功");

                      this.findPage()
                      this.$router.push('/AdminHome/Comments')   


                    })
                }).catch(()=>{
                    this.$message.info("取消操作");

    

      
         

     })
    },

    
    handleUpdate (blogid) {
      
      sessionStorage.setItem('admin_blogid',blogid)
      this.$router.push('/AdminHome/Postblogs')
     
    }

  }



}
</script>

<style lang="less" scoped>
  @import "../../assets/css/style.css";
  @import "../../assets/css/me.css";
  .blogs {
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>
