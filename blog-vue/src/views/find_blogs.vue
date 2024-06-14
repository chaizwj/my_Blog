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
        
          <el-table-column prop="index" type="index" min-width="15px" align="center" label="序号"></el-table-column>
        
          
        
          <el-table-column prop="createTime" label="发表时间" align="center"></el-table-column>
          <el-table-column prop="title" label="博客标题" align="center"></el-table-column>

          <el-table-column prop="type.typename" label="类型" align="center"></el-table-column>
        <el-table-column label="管理" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click.native="handleDelete(scope.row.blogid)">删除</el-button>
              <el-button size="mini" type="success" @click.native="handleUpdate(scope.row.blogid)">编辑</el-button>
              <!--              <el-button size="mini" type="info" @click="handleForbidden(scope.row)">禁用</el-button>-->
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


handleDelete (blogid) {
     


     this.$confirm("此操作永久删除当前信息，是否继续？","提示",{type:"info"}).then(()=>{
           
                    this.$axios.delete('/blog/'+blogid).then((res)=>{


                            if(res.data.flag)
                           this.$message({
                message: '删除成功',
                type: 'success'
              })

                      this.findPage()
                      this.$router.push('/About/find_blogs')   


                    })
                }).catch(()=>{
                    this.$message.info("取消操作");

                
     })
    },
   

    handleUpdate(blogid){
      sessionStorage.setItem('my_blogid',blogid)
       this.$router.push('/About/changeMyBlogs')
    },



// 分页查询  获取所有 发的   博客
     findPage () {
    
     this.$axios.get('/user/find_blogs/'+this.pagination.currentPage+'/'+this.pagination.pageSize+'/'+window.sessionStorage.getItem('uid')).then(res=>{
       
                         
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                   
                          this.dataList = res.data.data.records;    
                  
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
