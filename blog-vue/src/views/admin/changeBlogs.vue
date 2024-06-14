<template>
  <div class="blogs">
    <div class="content-header">
       <h1><small>修改博客</small></h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        
      </el-breadcrumb>

      





      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item ></el-breadcrumb-item>
        <el-breadcrumb-item></el-breadcrumb-item>
      </el-breadcrumb>
    </div>
      <div class="app-container">
        <div class="blog-box" style="">
        <el-form ref="addForm" :model="formData" :rules="rules" >
      
      
      
       <div class="two fields" style="margin-top: 3px">
           
           
            <el-form-item prop="typeid">
              <el-row>
                <el-select v-model="formData.typeid" placeholder="请选择分类" style="margin-left: 155px;float:left; width: 470px; margin-right: 8px">
                  <el-option
                    v-for="item in typeList"
                    :key="item.typeid"
                    :label="item.typename"
                    :value="item.typeid">
                  </el-option>
                </el-select>
              
              </el-row>
            </el-form-item>


                <el-form-item prop="shareStatement">
                <el-select v-model="formData.shareStatement" placeholder="请选择版权" style="margin-left: 155px;float:left; width: 470px; margin-right: 8px">
                  <el-option
                    v-for="item in shareStatementList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.name" >
                  </el-option>
                </el-select>
              </el-form-item>



          </div>



          
          <div class="required field">
            <div class="ui left labeled input">


              



              <el-form-item prop="title">
           
                <el-button type="primary" style="margin-left: 155px;">
                  <i class="el-icon-arrow-down el-icon-s-opportunity"></i>
                </el-button>

                <el-input v-model="formData.title" placeholder="请输入标题" style="margin-left: 0px;float:autp; width: 305px; margin-right: 8px"  ></el-input>
                   
              </el-form-item>
            </div>


          </div>



          <div class="field" style="margin-top: 8px;margin-left: 155px">
            <el-button type="primary">
            <el-upload
              class="avatar-uploader"
              action="serverApi/oss/userAvatar/"
              accept="image/png,.jpg"
              multiple
              :limit="1"
              :on-exceed="masterFileMax"
              :show-file-list="false"
              :http-request="uploadPic"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-arrow-down el-icon-picture"></i>
            </el-upload>
<!--              <i class="el-icon-arrow-down el-icon-picture"></i>-->

            </el-button>

            <el-input v-model="formData.firstpicture" style="width: 305px"></el-input>
           
            <el-form-item prop="description" style="margin-top: 8px">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 5}"
                placeholder="请写一下关于文章的摘要，这将让你的博客显示在首页时，帮助你吸引更多的读者"
                v-model="formData.description" style="margin-top: 10px;width: 655px">
              </el-input>
            </el-form-item>
            
          </div>







          <div class="inline fields" style="margin-top: 10px;margin-left: 155px">
            <el-checkbox v-model="formData.recommend">推荐</el-checkbox>
            
           
          </div>



           <div class="mavonEditor" style="margin-top: 30px;margin-left: 155px">
              
              
              <el-form-item prop="content" >


                    <!-- mavon-editor的使用，就是引入这个标签  -->
                <mavon-editor :codeStyle="markdownOption.codeStyle"
                              style="max-height: 500px;"
                              :scrollStyle="true"
                              :ishljs="true"
                              @imgAdd="handleEditorImgAdd"
                              @imgDel="handleEditorImgDel"
                              :toolbars="markdownOption"
                              v-model="formData.content"/>

                  </el-form-item>



          </div>


          <div class="ui right aligned container">
            <button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
            
            <button type="button" id="publish-btn" class="ui my-blue button" @click="add_Or_Update_Blog">发布</button>
          </div>

        </el-form>


        </div>
      </div>
    <br>
    <br>
  </div>
</template>

<script>
// import editormd from '../../../src/assets/lib/editormd/editormd.min.js'

export default {
  data () {
    return {
      imageUrl: '',
      imgFile: [],
      user: {},
      nickname: '',
      // 被激活的链接地址
     
     
     avatar: '',
      
      
      
      rules: { // 校验规则
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入正文内容', trigger: 'blur' },
          { min: 10, message: '长度最少是 10 个字符', trigger: 'blur' }
        ],
        typeid: [
          { required: true, message: '至少要有一个分类', trigger: 'blur' }
        ],
        shareStatement: [
          { required: true, message: '至少选择一个文章信息', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '麻烦写一个简单的摘要', trigger: 'blur' },
          { min: 10, max: 110, message: '长度在 10 到 110 个字符之间', trigger: 'blur' }
        ]
      },







      formData: {
        
        uid:1,
        shareStatement: '', // 版权状态
        typeid: '', // 分类id
        title: '', // 博客标题
        content: '#### 使用 markdown 编辑器来开始书写你的博客吧!&emsp;已经支持markdown编辑器上传图片的功能', // 正文文本
        firstpicture: '图片地址https://unsplash.it/800/450?image=1005', // 博客首图链接地址
      
        recommend: true, // 是否推荐
       
        commentabled: true, // 是否开启评论
       

        description: ''
      }, 
      
      


      // 表单数据
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预览
      },
      typeList: [],
      tagList: [],


      shareStatementList: [
        {
          id: 1,
          name: '原创'
        },
        {
          id: 2,
          name: '转载'
        },
        {
          id: 3,
          name: '翻译'
        }
      ],
      contentEditor: '',


      markdownOption: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true, // 预览
        codeStyle: 'monokai-sublime'
      }




    }
  },
  created () {

    
    this.getTypeList()
   
    this.get_if_blog()
    
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




    handleEditorImgAdd (pos, $file) {
      const formData = new FormData()
      formData.append('file', $file)
      this.imgFile[pos] = $file
      this.$http.post('/serverApi/oss/articleImage', formData).then(res => {
        if (res.data.flag) {
          this.$message.success('上传成功')
          const url = res.data.data.url
          let name = $file.name
          if (name.includes('-')) {
            name = name.replace(/-/g, '')
          }
          const content = this.formData.content
          // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
          if (content.includes(name)) {
            const oStr = `(${pos})`
            const nStr = `(${url})`
            const index = content.indexOf(oStr)
            const str = content.replace(oStr, '')
            const insertStr = (soure, start, newStr) => {
              return soure.slice(0, start) + newStr + soure.slice(start)
            }
            this.formData.content = insertStr(str, index, nStr)
          }
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    handleEditorImgDel (pos) {
      delete this.imgFile[pos]
      this.$message.error('暂时无法删除图片！')
    },
    getUser () {
      this.user = window.sessionStorage.getItem('user')
      this.nickname = JSON.parse(this.user).nickname
      this.avatar = JSON.parse(this.user).avatar
    },
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/login')
      // 刷新页面，删除vuex数据
      window.location.reload()
    },





    add_Or_Update_Blog () {
      // 进行表单校验
      this.$refs.addForm.validate((valid) => {
        if (valid) {


              
          // 表单校验通过，发axios请求，把数据录入至后台处理
       
         if(sessionStorage.getItem('admin_blogid'))
         {  
          
        
           this.$axios.put('/blog', this.formData).then((res) => {
            
            
            if(res.data.flag){
              
              // 弹出提示信息
            this.$message({
                message: '更新成功',
                type: 'success'
              })
              //再删除这个sessionStorage里面的id ，不然会影响后面的添加操作。
              sessionStorage.removeItem('admin_blogid')
              
              }
               this.$router.push('/adminHome/Blogs')
              

          })

         
         }

         else{   

          
          this.formData.uid=window.sessionStorage.getItem('uid')
          this.$axios.post('/blog', this.formData).then((res) => {
            // 关闭新增窗口



            this.dialogFormVisible = false
            if (res.data.flag) {
              // 弹出提示信息
              this.$message({
                message: '发表成功',
                type: 'success'
              })
             this.$router.push('/adminHome/Blogs')
            } 
          })
         
         }


        } 


      })
    },



        get_if_blog(){     
          
          
            
    if(sessionStorage.getItem('admin_blogid'))
    {  
      
      const admin_blogid = sessionStorage.getItem('admin_blogid')
      
      
      this.$axios.get(`/blog/admin/${admin_blogid}`).then(res=>{
     
       
        console.log('oneblog',res.data)
        this.formData=res.data.data
       
       console.log('current_formData',this.formData)

      
      })   
      
      }

    



      },


    // 获取所有的分类并回显
     getTypeList () {
      this.$axios.get('/home/getType').then(res=>{
       
        
        this.typeList = res.data.data
      })
      
    },
   



  }

  


}
</script>

<style scoped>
  


   
body {
    background: url("../../assets/images/bg.png");
}

.m-padded-mini {
    padding: 0.2em !important;
}

.m-padded-tiny {
    padding: 0.3em !important;
}
.m-padded {
    padding: 1em !important;
}
.m-padded-tb-mini {
    padding-top: 0.2em !important;
    padding-bottom: 0.2em !important;
}
.m-padded-tb-tiny {
    padding-top: 0.3em !important;
    padding-bottom: 0.3em !important;
}
.m-padded-tb-small {
    padding-top: 0.5em !important;
    padding-bottom: 0.5em !important;
}
.m-padded-tb {
    padding-top: 1em !important;
    padding-bottom: 1em !important;
}
.m-padded-tb-large {
    padding-top: 2em !important;
    padding-bottom: 2em !important;
}
.m-padded-tb-big {
    padding-top: 3em !important;
    padding-bottom: 3em !important;
}
.m-padded-tb-huge {
    padding-top: 4em !important;
    padding-bottom: 4em !important;
}
.m-padded-tb-massive {
    padding-top: 5em !important;
    padding-bottom: 5em !important;
}


.m-padded-lr-responsive {
    padding-left: 4em !important;
    padding-right: 4em !important;
}

/*-------text -----*/
.my-blue {
    color: #1685a9 !important;
}

.m-text {
    font-weight: 300 !important;
    letter-spacing: 1px !important;
    line-height: 1.8;
}
.m-text-thin {
    font-weight: 300 !important;
}
.m-text-spaced {
    letter-spacing: 1px !important;
}
.m-text-lined {
    line-height: 1.8;
}

/*-----margin------*/

.m-margin-top-small {
    margin-top:0.5em !important;
}
.m-margin-top {
    margin-top: 1em !important;
}
.m-margin-top-large {
    margin-top: 2em !important;
}

.m-margin-tb-tiny {
    margin-top: 0.3em !important;
    margin-bottom: 0.3em !important;
}

.m-margin-bottom-small {
    margin-bottom: 0.5em !important;

}

/*-----opacity------*/
.m-opacity-mini {
    opacity: 0.8 !important;
}
.m-opacity-tiny {
    opacity: 0.6 !important;
}
/*------position---*/

.m-right-top {
    position: absolute;
    top:0;
    right: 0;
}

/*------display------*/
.m-inline-block {
    display: inline-block !important;
}


/*-----container*/

.m-container {
    max-width: 72em !important;
    margin: auto !important;
}
.m-container-small {
    max-width: 60em !important;
    margin: auto !important;
}

/*------shadow------*/
.m-shadow-small {
    -webkit-box-shadow: 0 4px 8px rgba(0,0,0,0.2) !important;
    box-shadow: 0 4px 8px rgba(0,0,0,0.2) !important;
}

/*------positon-----*/

.m-fixed {
    position: fixed !important;
    z-index: 10 !important;
}

.m-right-bottom {
    bottom: 0 !important;
    right: 0 !important;
}


/*-----color-----*/
.my-blog-shadow:hover{
    border-radius: 8px !important;
    box-shadow: 0px 4px 12px 12px rgba(7, 17, 27, 0.15) !important;
}
.my-shadow {
    background: #fff !important;
    color: #4c4948;
    border-radius: 8px !important;
    box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06) !important;
    transition: all 0.3s;
}
.my-shadow:hover {
    box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15) !important;
}
.m-black {
    color: #333 !important;
}

.m-mobile-show {
   display: none !important;
}

@media screen and (max-width: 768px){
    .m-mobile-hide {
        display: none !important;
    }
    .m-mobile-show {
        display: block !important;
    }
    .m-padded-lr-responsive {
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .m-mobile-lr-clear {
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .m-mobile-wide {
        width: 100% !important;
    }
}
















.mavonEditor {
  width: 75%;
  height: 100%;
  margin: 0 auto;
}
  .blog-box {
    position: relative;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #3c8dbc;
    padding: 10px;
    margin-bottom: 20px;
    width: 100%;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
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
