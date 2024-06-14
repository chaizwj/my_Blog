<template>
  <div class="blogs">
    <div class="content-header">
      <h1><small>个人信息</small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        
      </el-breadcrumb>
    </div>

    <!--中间内容-->
    <div class="app-container">
      <div class="box">




        <el-form :model="formData" :rules="userRules" ref="userForm" label-width="100px">
         
          <el-row>
            <el-col :span="8">
              <el-form-item prop="username" label="用户名" >
                <el-input v-model="formData.username" ></el-input>
              </el-form-item>
            </el-col>


            <el-col :span="8">
              <el-form-item prop="password" label="密码">
                <el-input v-model="formData.password"></el-input>
              </el-form-item>
            </el-col>
            
          </el-row>
         



            <el-form-item prop="avatar" label="头像地址">
             
              <el-input style="width: 78%" v-model="formData.avatar"></el-input>
            
           
            </el-form-item>

           

          <el-row>
            <el-col :span="8">
              <el-form-item prop="username" label="用户状态" >
                <el-input :value="state" type="text"></el-input>
              </el-form-item>
            </el-col>
            
            <el-col>

               <!--           申请博主           -->
               <el-form-item prop="password" label="申请博主" v-show="formData.isFlag==0">
                      
                            <el-switch  v-model="value" @change="accept">     </el-switch>

                    </el-form-item>
            </el-col>
           
                    
                    </el-row>


          <el-form-item>


                    


            <el-button type="primary" @click="submitForm" >立即修改</el-button>
<!--            <el-button @click="resetForm('formData')">重置</el-button>-->
          </el-form-item>




        </el-form>

        
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

      value:false,

      imageUrl: '',

    //表单绑定的数据， 
      formData: {},



      userRules: {
        // 验证用户名是否合法
        
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
      
        avatar: [
          { required: true, message: '请输入头像地址', trigger: 'blur' },
          { min: 1, max: 150, message: '长度在 1 到 150 个字符', trigger: 'blur' }
        ]
      }
    }
  },


  created () {


    this.getUser()


  },
 computed:{
     state(){

                 let text='' 
            if(this.formData.isFlag==0)
            { 
                text = '普通用户'
                }

            else if(this.formData.isFlag==1)
            {
                text='申请博主中'
                }

            else if(this.formData.isFlag==2)
            {
                text='博主'
            }
            else if(this.formData.isFlag==-1)
            {
                text='管理员'
            }

          return text

     }

 },



  methods: {

       accept(){

                    this.formData.isFlag = 1
                    

                    this.$message.success('已经申请博主，等待管理员审核！')

          
          },
    masterFileMax (files, fileList) {
      console.log(files, fileList)
      this.$message.warning('请最多上传一张图片')
    },




     uploadPic (param) {
      var fileObj = param.file
      var form = new FormData()
      // 文件对象
      form.append('file', fileObj)
      const { data: res } =  this.$http.post('/serverApi/oss/userAvatar', form)
      if (res.flag) {
        // 弹出提示信息
        this.$message.success('上传头像成功')
        this.formData.avatar = res.data.url
        console.log(res.data.url)
      } else { // 执行失败
        this.$message.error(res.message)
      }
    },



    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },



    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt5M = file.size / 1024 / 1024 < 5

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt5M) {
        this.$message.error('上传头像图片大小不能超过5MB!')
      }
      return isJPG && isLt5M
    },


        //  更新个人信息
    submitForm () {
      // 进行表单校验
      this.$refs.userForm.validate((valid) => {
        if (valid) 
        
        {
          // 表单校验通过，发ajax请求，把数据录入至后台处理
          this.formData.uid = sessionStorage.getItem('uid')
          
          console.log(this.formData.username)
          console.log(this.formData.uid)
          this.$axios.put('/user', this.formData).then((res) => {
            

            this.getUser()

            if (res.data.flag) {
              // 弹出提示信息
              this.$message({
                message: '修改成功！',
                type: 'success'
              })
            } else { // 执行失败
              this.$message.error(res.data.message)
            }
          })
        } 
        
        
        
        else 
        { // 校验不通过
          this.$message.error('校验失败，请检查输入格式')
          return false
        }


      })
    },


    resetForm (formName) {
      this.$refs[formName].resetFields()
    },




      //先获取当前用户的个人信息 
    getUser () {


       this.$axios.get('/user/'+sessionStorage.getItem('uid')).then((res) => {
            // 关闭新增窗口

            
            this.formData = res.data.data
           
          })



    }
  }





}
</script>

<style  scoped>

  @import "../assets/css/me.css";
  @import "../assets/css/style.css";

</style>
