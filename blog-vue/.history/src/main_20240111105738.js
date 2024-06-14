//关闭Vue的生产提示
Vue.config.productionTip = false


//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router'
//引入mavon-editor 编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
 //  引入echart 插件
import echarts from 'v-charts'
//引入 发送请求的对象。axios
import axios from 'axios'
//完整引入	ElementUI组件库
import ElementUI from 'element-ui'
//引入	ElementUI全部样式
import 'element-ui/lib/theme-chalk/index.css'

123123


//应用路由插件
Vue.use(VueRouter)
//应用ElementUI
Vue.use(ElementUI)
// 应用 mavon-editor 
Vue.use(mavonEditor)




//添加axios 到《原型》对象上,这样就很方便。就不需要每个Vue里面再重新import 一下 axios了
Vue.prototype.$axios=axios
Vue.prototype.$echarts=echarts



//创建vm
new Vue({
	
	el:'#app',
	render: h => h(App),


	//  因为 键值对，如果同名，就可以省略value值（ES6新写法）
	router
})
