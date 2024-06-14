<template>
    <div class="login_container">
      <!-- banner -->
      <div class="archive-banner banner">
        <h1 class="banner-title">归档</h1>
      </div>
      <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/images/login_icon.png" alt="">
      </div>
        <!-- 登录表单区域 -->
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
          <!-- 用户名 -->
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user-solid"></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-moon-night" type="password"></el-input>
          </el-form-item>
         
         
          <el-form-item prop="code">




          






          </el-form-item>


          <!-- 按钮区域 -->
          <el-form-item class="btns">
            <el-button type="success" @click="handleCreate">注册</el-button>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="resetLoginForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>



    

      <!-- 注册弹层-->
      <div class="add-form">
        <el-dialog title="注册用户" :visible.sync="dialogFormVisible">
          <el-form ref="registForm" :model="formData" :rules="loginFormRules" label-position="right"
                   label-width="100px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="用户名" prop="username">
                  <el-input label="请输入用户名" v-model="formData.username"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="密码" prop="password">
                  <el-input  show-password  v-model="formData.password"/>
                </el-form-item>
              </el-col>
            </el-row>
          <el-row>
            
          
          </el-row>


            <el-col :span="24">
              <el-form-item label="头像地址">
                <el-input v-model="formData.avatar"/>
              </el-form-item>
            </el-col>





          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="regist()">确定</el-button>
          </div>
        </el-dialog>
      </div>








    </div>
</template>

<script>
export default {
  data () {
    return {
      imageUrl: '',
      verifyCode: '',
    
    


    // 注册  的表单 
    formData: {
        username: '',
        password: '',
    
      
        avatar: '头像参考地址：https://unsplash.it/800/450?image= 1032 这里写 一个数字即可'
      },
      dialogFormVisible: false, // 增加表单是否可见
    
    
    
    
    
    // 这是  登录  表单  的数据绑定对象
      loginForm: {
        username: '',
        password: '',
        verKey: '',
        code: ''
      },


      // 这是表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
       
        nickname: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },



  created () {
    this.getVerifyCode()
  },


  methods: {
    masterFileMax (files, fileList) {
      console.log(files, fileList)
      this.$message.warning('请最多上传一张图片')
    },
    async uploadPic (param) {
      var fileObj = param.file
      var form = new FormData()
    

    // 文件对象
      form.append('file', fileObj)
      const { data: res } = await this.$http.post('/serverApi/oss/userAvatar', form)
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






    async getVerifyCode () {
      this.$axios.get('/code/getCode').then(res=> {  
      
      this.verifyCode = res.data
      
      this.loginForm.verKey = res.code
      
      }

    
      )
      
      // this.$message({
      //   message: '部分功能只在用户登录后开发',
      //   type: 'info'
      // })
    },



    // 点击重置按钮，重置登录表单
    resetLoginForm () {
      this.$refs.loginFormRef.resetFields()
    },
    // 弹出添加窗口
    handleCreate () {
      this.dialogFormVisible = true
    },



    regist () {

      // 进行表单校验
      this.$refs.registForm.validate((valid) => {

        if (valid) {

        
         
          this.$axios.post('/user',this.formData).then((res) => {

        

          // 关闭新增窗口
            this.dialogFormVisible = false
            if (res.data.flag) {
              // 弹出提示信息
              this.$message({
                message: '注册成功，快来登录吧！',
                type: 'success'
              })
            }



          })
        } 
        
        



      })
    },





        //    校验 用户密码是否正确，并且从后端传来一个token令牌

    login () {

      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return
       
         
        this.$axios.get('/user/login'+'/'+this.loginForm.username).then(res=>{ 
          
         
          
         window.sessionStorage.setItem('uid',res.data.data.uid)
        window.sessionStorage.setItem('username',res.data.data.username)



          console.log('login_token',res.data)
            // 1. 将登录成功之后的 token，保存到客户端的 sessionStorage 中
          //   1.1 项目中出了登录之外的其他API接口，必须在登录之后才能访问
          //   1.2 token 只应在当前网站打开期间生效，所以将 token 保存在 sessionStorage 中
         
         window.sessionStorage.setItem('token', res.data.data.token)
        //  JSON.stringify 方法是内置的，用来把后端的传来的对象转换为 字符串！！
        // 登入之后，从后端传来的对象一般都是要保存在sessionStorage里面，用于之后的评论操作啥的，提供用户数据
         window.sessionStorage.setItem('user', JSON.stringify(res.data.data))

            
            // 2. 通过   编程式路由    根据 你的账号 选择跳转到后台主页 还是管理员界面 
           if(this.loginForm.password==res.data.data.password)
            {   


                if(this.loginForm.username=='admin')
                  {
                    this.$message.success('登录成功')
                    this.$router.push('/adminHome')

                  }
                  
                  else{
                     this.$message.success('登录成功')
                this.$router.push('/home')
                  }
               


            }
            else{

               this.$message.error("登入失败,密码错误，请重新输入")
               this.resetLoginForm()
            }
           
        })
        
        
          
          
          
         
        


      })

      }



  }
}
</script>

<style lang="less" scoped>
  .banner-title {
    animation: title-scale 1s;
    position: absolute;
    top: 25rem;
    padding: 0 0.5rem;
    width: 100%;
    font-size: 2.5rem;
    text-align: center;
    color: #eee;
  }
  .archive-banner {
    height: 110vh;
    background: url("../assets/images/bg7.png") center center /
   cover no-repeat;
    background-color: #49b1f5;
  }
  .login_container {
    background-color: #2b4b6b;
    height: 100%;
  }
  .login_box {
    width: 450px;
    height: 360px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .avatar_box {
      height: 130px;
      width: 130px;
      border: 1px solid #eee;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #ddd;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: #fff;
      img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
      }
    }
  }

  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }

  .btns {
    display: flex;
    justify-content: flex-end;
  }
  .avatar-uploader .el-upload {
    border: 5px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 60px;
    height: 60px;
    display: block;
  }
</style>
