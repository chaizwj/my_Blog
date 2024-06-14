<template>
  <div class="blogs">
    <div class="content-header">
      <h1>博客管理<small></small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item >后台管理</el-breadcrumb-item>
        <el-breadcrumb-item>博客管理</el-breadcrumb-item>
        <el-breadcrumb-item>分类管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <!--中间内容-->
    <div class="app-container">
      <div class="box">

        <div class="filter-container">
          





        

          <el-button type="primary" class="butT" @click="dialogFormVisible1=true" style="margin-left:50px">添加分类</el-button>


        </div>

      <div class="ui container">
        <el-table size="middle" current-row-key="id" :data="typeList" stripe highlight-current-row>
          <!--                        id,用户名，真实姓名，角色，备注，最后登录时间，创建时间-->
          <el-table-column prop="typeid" min-width="15px" align="center" label="序号"></el-table-column>
          <el-table-column prop="typename" label="分类名称" align="center"></el-table-column>
          <el-table-column label="管理" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="handleDelete(scope.row.typeid)">删除</el-button>
            <el-button size="mini" type="success" @click="handleUpdate(scope.row.typeid)">编辑</el-button>

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
            :page-sizes="[2,5,10,15]"
            :page-size="pagination.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total">
          </el-pagination>
        </div>
       
       
        <!-- 新增标签弹层 手机号 真实姓名（*） 密码 备注 角色   el-dialog 组件 -->
      
       <div class="add-form">



         <el-dialog title="新增分类" :visible.sync="dialogFormVisible1">
         
         
           <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"  label-width="100px">
             <el-row>
               
               <el-col :span="12">
                 <el-form-item label="分类名" prop="typename">
                   <el-input v-model="formData.typename"/>
                 </el-form-item>
               </el-col>
             </el-row>


             
           </el-form>



           <div slot="footer" class="dialog-footer">
             <el-button @click="cancel1">取消</el-button>
             <el-button type="primary" @click="handleAdd()">确定</el-button>
           </div>
         </el-dialog>


       </div>








<!--                  修改    -->
         <el-dialog title="编辑分类" :visible.sync="dialogFormVisible2">
         
         
           <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"  label-width="100px">
             <el-row>
               
               <el-col :span="12">
                 <el-form-item label="分类名" prop="typename">
                   <el-input v-model="formData.typename"/>
                 </el-form-item>
               </el-col>
             </el-row>


             
           </el-form>



           <div slot="footer" class="dialog-footer">
             <el-button @click="cancel2">取消</el-button>
             <el-button type="primary" @click="Update">确定</el-button>
           </div>
         </el-dialog>





          <AddTypes ref="addTypes"></AddTypes>




      </div>
    </div>
    </div>
    <br>
    <br>
  </div>
</template>


<script>

import AddTypes from '../../components/admin/AddTypes'


export default {
  components: {
    AddTypes
  },
  data () {

    return {


      pagination: { // 分页相关模型数据
        currentPage: 1, // 当前页码
        pageSize: 10, // 每页显示的记录数
        total: 0, // 总记录数
        queryString: null // 查询条件
      },

                  //  对话框 中的 表单数据
      formData: {
        typeid:'',
        typename:''
      }, 


      dialogFormVisible1: false, // 编辑表单是否可见
      dialogFormVisible2: false, // 编辑表单是否可见
     
     typeList: [], // 当前页要展示的   分类名


      user: {},
      nickname: '',
      // 被激活的链接地址
      avatar: '',


      rules: { // 校验规则
       
       
       // 分类名称
        typename: [
          { required: true, message: '请输入分类名称', trigger: 'blur' },
          { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },


  created () {
    this.getTypeList()
  },


  methods: {


    cancel1(){
      this.dialogFormVisible1=false
      this.formData.typename= ''
    },

    cancel2(){
      this.dialogFormVisible2=false
      this.formData.typename= ''
    },
  getTypeList () {
     
          this.$axios.get('/home/getType').then(res=>{
              console.log('type',res.data)
             this.typeList = res.data.data 
           
     })     

    },

  

      // 添加按钮
  handleAdd () {
    
          
          this.$axios.post('/type', this.formData).then((res) => {
            // 关闭新增窗口
            this.dialogFormVisible1 = false
             this.formData.typename = ''
            if (res.data.flag) {
              // 新增成功后，重新调用分页查询方法，查询出最新的数据
              this.getTypeList()
              // 弹出提示信息
              this.$message({
                message: '添加成功',
                type: 'success'
              })
            } else { // 执行失败
              this.$message.error(res.data.code)
            }
          })
 
    },



      //   修改 


      handleUpdate(typeid){

            this.dialogFormVisible2=true
            sessionStorage.setItem('typeid',typeid)

      },


      Update(){
        this.formData.typeid = sessionStorage.getItem('typeid')

        
        console.log(this.formData.typeid)
this.$axios.put('/type', this.formData).then((res) => {
            // 关闭编辑窗口
            this.dialogFormVisible2 = false
             this.formData.typename = ''
            if (res.data.flag) {
              // 修改成功后，重新调用分页查询方法，查询出最新的数据
              this.getTypeList()
              // 弹出提示信息
              this.$message({
                message: '编辑成功',
                type: 'success'
              })
            } else { // 执行失败
              this.$message.error(res.data.code)
            }
          })

      },



    /// 处理  删除 分类  的操作



     handleDelete (typeid){
     


     this.$confirm("此操作永久删除当前信息，是否继续？","提示",{type:"info"}).then(()=>{
           
                    this.$axios.delete('/type/'+typeid).then((res)=>{


                            if(res.data.flag)
                            this.$message.success("删除成功")

                      this.getTypeList()
                       


                    })
                }).catch(()=>{
                    this.$message.info("取消操作");

    

      
         

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
      // 重新调用getTypeList
      this.getTypeList()
    },


    handleSizeChange (newSize) {
      this.pagination.pageSize = newSize
      this.getTypeList()
    },



    // 弹出添加窗口
    handleCreate () {
      // this.dialogFormVisible = true
      this.$refs.addTypes.handleCreate()
    }


   
  }
}
</script>

<style lang="less" scoped>
  @import "../../assets/css/me.css";
  @import "../../assets/css/style.css";
</style>
