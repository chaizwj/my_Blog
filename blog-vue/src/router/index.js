// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件


// 前端用户界面的路由
import Home from '../views/Home.vue'
import Types from '../views/Types.vue'

import About from '../views/About.vue'
import Blog from '../views/Blog.vue'
import Login from '../views/Login.vue'
import PostBlogs from '../views/PostBlogs.vue'
import personal from '../views/personal.vue'
import Message from '../views/Message.vue'
import find_thumbs from '../views/find_thumbs.vue'
import find_saves from '../views/find_saves.vue'
import find_blogs from '../views/find_blogs.vue'
import changeMyBlogs from '../views/changeMyBlogs.vue'
import Crawler from '../views/Crawler.vue'
import CrawlerBlog from '../views/CrawlerBlog.vue'


//  管理员界面的路由
import adminHome from '../views/admin/adminHome.vue'
import Blogs from '../views/admin/Blogs.vue'
import Users from '../views/admin/Users.vue'
import Types2 from '../views/admin/Types2.vue'
import thumbChart from '../views/admin/thumbChart.vue'
import viewChart from '../views/admin/viewChart.vue'

import Report2 from '../views/admin/Report2.vue'
import changeBlogs from '../views/admin/changeBlogs.vue'   
import changeUser from '../views/admin/changeUser.vue' 
import Comments from '../views/admin/Comments.vue'






//创建并暴露一个路由器
export default new VueRouter({

	routes:[

		{	path:'/',
			redirect:'/home'
		},
		{
			path:'/home',
			component:Home
		},
		
		{
			path:'/Crawler',
			component:Crawler
		},
		

		{
			path:'/Message',
			component:Message
		},
		{
			path: '/Types',
			name: 'Types',
			component: Types
		},

		{
		path: '/About',
		name: 'About',
		component: About,
			children: [ 	
			{
				path:'PostBlogs',
				component:PostBlogs
			},
			{
				path:'personal',
				component:personal
			},
			{
				path:'find_thumbs',
				component:find_thumbs
			},
			{
				path:'find_saves',
				component:find_saves
			},
			{
				path:'find_blogs',
				component:find_blogs
			},
			{
				path:'changeMyBlogs',
				component:changeMyBlogs
			}





			]

		
		},

		
		
		{
		path: '/Blog',
		name: 'Blog',
		component: Blog
		},
		{
			path: '/CrawlerBlog',
			name: 'CrawlerBlog',
			component: CrawlerBlog
			},


		{
			path: '/Login',
			component:Login
		},
		{
			path: '/adminHome',
			component: adminHome,
			
			children: [ 
		
				
				{ path: 'Blogs', component:Blogs},
				{ path: 'Users', component:Users},
				{ path: 'Types2', component: Types2 },
				{ path: 'thumbChart', component: thumbChart },
				{ path: 'viewChart', component: viewChart },
				
				{ path: 'report2', component: Report2 },
				
				{ path: 'Comments', component: Comments },
				{ path: 'changeBlogs', component: changeBlogs },
				{ path: 'changeUser', component: changeUser },
			
				
	
			]
		}

		
		
	]
})
