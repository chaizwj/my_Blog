## 🍋简介
这是一个 SpringBoot + Vue 搭建的个人博客网站，支持网页端和手机移动端自适应，markdown编写文章，拥有流式布局。采用前后端分离进行实现，前端项目通过vue开发，后端使用springboot进行开发。最好的一个巩固自己所学的知识，就是拿一个的项目来练练手，并且能通过自己重构项目，很好的在编码过程中总结和发现问题、解决问题，去探索新知识 。  

### 目前功能
- 博客主页附上了个人的联系方式   
- 博客汇总，以列表形式展示文章，并附上发布日期、分类情况、文章简要以及博客首图
- 能够以分类形式查看文章
- 能够以时间列表方式归档文章
- 可实现通过文章内容关键字或者标题查找所有相关文章    
- 网站仪表盘，记录网站访客量、文章发布的总数等情况   

### 未来展望 
- 可以添加博客评论的功能
- 目前来说仅仅是自己发文章给其他访客阅读，可以考虑扩展成多用户都可以使用，并且发布博客
- 可以建立一个聊天室
## 🍋网站示例

这个是我的个人博客网站地址  [sadie.richfan.cn](https://sadie.richfan.cn/)

## 🍋技术栈
* 后端：`SpringBoot`，`SSM`，`Mybatis-Plus`，`MySQL`，`Druid`，`Lombok`，`PageHelper`，`jwt`
* 前端：`Vue`，`Echarts`，`Element UI`，`HTML`，`CSS`，`Javacript`，`nginx`

## 🍋环境

|  工具 | 名称 
| ------------ | ------------
| 编译器  | IDEA2020、 Visual studio
| 数据库工具  | Navicat 15 
|  语言 | JDK1.8 
| 项目构建  | Maven 
## 🍋本地启动
### 前端
用Visual studio编译器打开 blog-vue文件夹之后，在下面的terminal命令行中运行以下命令
```bash
npm install
npm run serve
```
然后就可以在浏览器中输入 localhost:8081 ，来进行访问前端界面


### 后端

- **导入数据库：** 将blog-springboot文件夹导入之后，在它的根目录下有一个blog.sql文件，将它在navicat中导入，创建对应的数据库表
- **使用maven进行打包：** 在IDEA 2020 编译器下面的 terminal 中运行下面的命令
```
mvn clean package
```
- **在target文件夹下找到生成的jar包**：然后在IDEA 2020中右键选中它，点击Open In-Explorer，进去到对应的系统文件夹，在当前文件夹下面，打开cmd窗口，然后运行下面的命令
```bash
java -jar blog-springboot-0.0.1-SNAPSHOT.jar
```
这样就启动了后端项目，端口是8098

## 🍋有空会持续更新...

