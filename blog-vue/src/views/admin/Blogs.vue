<template>
  <div class="blogs">
    <div class="content-header">
      <h1>博客管理<small></small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item >后台管理</el-breadcrumb-item>
        <el-breadcrumb-item>博客管理</el-breadcrumb-item>
        <el-breadcrumb-item>查看博客</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!--中间内容-->
    <div class="app-container">
    <div class="box">
<!--    <div  class="m-container-small m-padded-tb-big">-->
      <div class="filter-container">
       
        
       

         <el-row>
            <el-col :span="6">
               <el-select v-model="value" placeholder="按分类查询"  style="margin-left:50px">
            <el-option
              v-for="item in typeList"  
              :key="item.typeid"
              :label="item.typename"
              :value="item.typename"
              @click.native="findBlogBytype(item.typeid)">
            </el-option>

        </el-select> 
            </el-col>


            <el-col :span="4">
             <el-button size="mini" type="warning" @click.native="findPage">查询全部</el-button>
            </el-col>
            
          </el-row>

      
     
      </div>


      <div class="ui container">


        <el-table size="middle" current-row-key="id" :data="dataList" stripe highlight-current-row>
          <!--                        id,用户名，真实姓名，角色，备注，最后登录时间，创建时间-->
          <el-table-column prop="index" type="index" min-width="15px" align="center" label="序号"></el-table-column>
        
          <el-table-column prop="title" label="标题" align="center"></el-table-column>
        
          <el-table-column prop="type.typename" label="类型" align="center"></el-table-column>
        

        <!--  slot 插槽的用法  -->
          <el-table-column label="推荐" prop="recommend">


            <template slot-scope="scope">
              
              <el-switch v-model="scope.row.recommend"
                         @change="recommendStateChanged(scope.row)">
              </el-switch>

            </template>

          </el-table-column>





         
          <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
        
        
          <el-table-column label="管理" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click.native="handleDelete(scope.row.blogid)">删除</el-button>
              <el-button size="mini" type="primary" @click.native="handleUpdate(scope.row.blogid)">编辑</el-button>
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
        <!-- 新增标签弹层 手机号 真实姓名（*） 密码 备注 角色-->
        <div class="add-form">
          <el-dialog title="新增分类" :visible.sync="dialogFormVisible">
            <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"
                     label-width="100px">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="分类编号" prop="typeId">
                    <el-input v-model="formData.typeId"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="分类名" prop="name">
                    <el-input v-model="formData.name"/>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="handleAdd()">确定</el-button>
            </div>
          </el-dialog>
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

      options: [
        { label: '个人编写的博客数较少，本查询功能接口暂未实现' }
        
      ],


      pagination: { // 分页相关模型数据
        currentPage: 1, // 当前页码
        pageSize: 10, // 每页显示的记录数
        total: 0, // 总记录数
        queryString: null // 查询条件
      },
      formData: {}, // 表单数据
      dialogFormVisible: false, // 增加表单是否可见
      dialogFormVisible4Edit: false, // 编辑表单是否可见
    
    
    dataList: [], // 当前页要展示的分页列表数据

    typeList:[],

     
     
      avatar: '',
      rules: { // 校验规则
        // 真实姓名
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' },
          { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },


  created () {


    this.findPage()
 
    this.getTypeList()

  },


  methods: {



   

      getTypeList () {
     

          this.$axios.get('/home/getType').then(res=>{
              console.log('type',res.data)
             this.typeList = res.data.data 

             

     })     



    },




// 分页查询
     findPage () {
    
     this.$axios.get('/blog/findAdminHomePage/'+this.pagination.currentPage+'/'+this.pagination.pageSize).then(res=>{
       
                          console.log('home',res.data)
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                       
                       
                          this.dataList = res.data.data.records;    
                       
                       

                          
     })    


     },







     findBlogBytype(typeid)
     {            
                  
       this.$axios.get('/home/findAdmin_Blogs_ByType/'+this.pagination.currentPage+'/'+this.pagination.pageSize+'/'+typeid).then(res=>{
       
                          console.log('admin_typeByid',res.data)
                         this.pagination.pageSize = res.data.data.size;
                         this.pagination.currentPage = res.data.data.current;
                         this.pagination.total = res.data.data.total;
                       
                       
                       
                          this.dataList = res.data.data.records;           
              

     })    



     },








    // 添加按钮
    handleAdd () {
      // 进行表单校验
      this.$refs.dataAddForm.validate((valid) => {


        if (valid) {
          // 表单校验通过，发ajax请求，把数据录入至后台处理
          // const param = this.$encrypTion(JSON.stringify(this.formData))
          this.$http.post('/api/types2/add', this.formData).then((res) => {
            // 关闭新增窗口
            this.dialogFormVisible = false
            if (res.data.flag) {
              // 新增成功后，重新调用分页查询方法，查询出最新的数据
              this.findPage()
              // 弹出提示信息
              this.$message({
                message: '添加成功',
                type: 'success'
              })
            } else { // 执行失败
              this.$message.error(res.data.code)
            }
          })
        } else { // 校验不通过
          this.$message.error('校验失败，请检查输入格式')
          return false
        }
      })
    },


    // 监听 switch 开关状态的改变
     recommendStateChanged (row) {
      

      console.log('row',row)

       if(row.recommend==true) {

            //用 put请求，更新
        this.$axios.put('/blog',row).then(res=>{ 

            if(res.data.flag){
              this.$message.success("设置推荐到首页")
            }

        })

       }

       else{ 

         //用 put请求，更新
        this.$axios.put('/blog',row).then(res=>{ 

            if(res.data.flag){
              this.$message.error("取消推荐")
            }

        })
       
      }
       
      
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


    handleDelete (blogid) {
     


     this.$confirm("此操作永久删除当前信息，是否继续？","提示",{type:"info"}).then(()=>{
           
                    this.$axios.delete('/blog/'+blogid).then((res)=>{


                            if(res.data.flag)
                            this.$message.info("删除成功");

                      this.findPage()
                      this.$router.push('/AdminHome/Blogs')   


                    })
                }).catch(()=>{
                    this.$message.info("取消操作");

                
     })
    },

  
    handleUpdate (blogid) {
      
      sessionStorage.setItem('admin_blogid',blogid)
      this.$router.push('/AdminHome/changeBlogs')
     
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
