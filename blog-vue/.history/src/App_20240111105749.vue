<template>
  <div class="b">


          <TopNavBar v-show=" $route.path=='/home' || $route.path=='/types'     || $route.path=='/About'   || $route.path=='/blog'   || $route.path=='/Message'     || $route.path=='/About/PostBlogs'    || $route.path=='/About/find_thumbs'    || $route.path=='/About/personal'    || $route.path=='/About/find_saves'    || $route.path=='/About/getMYBlogs'   || $route.path=='/About/find_blogs'   || $route.path=='/Crawler'      || $route.path=='/CrawlerBlog'  || $route.path=='/About/changeMyBlogs'">
          </TopNavBar>
				
        
     
						<!-- 指定组件的呈现位置 -->           
             <router-view  ></router-view>

         
      
   

  
  </div>



</template>

<script>
 // import  引入组件  
import TopNavBar from './components/TopNavBar'

im
   //   export 默认暴露 
	export default {
    
  data () {
    return {
      path: '',
        
        
    }
  },
		name:'App',
    components:{
      TopNavBar,
       
    },
    

    mounted () {
    this.path = this.$route.path
    // console.log(this.$route.path)
  }
	}
  
</script>



   

