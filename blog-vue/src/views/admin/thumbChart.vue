<template>
  
  
      <!-- 2. 为ECharts准备一个具备大小（宽高）的Dom -->
     <div id="myChart" :style="{width: '800px', height: '600px',marginLeft:'350px'}"></div>
  

</template>





<script>
export default {

mounted (){
     
     
         
        this.$axios.get('/echart/barVOListThumb').then(res=>{ 

            console.log(res.data.data.values)
          // 必须这么写。   下面两行代码。
          const echarts = require('echarts')
          // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('myChart'))

          //   这么写不行的。 - - - -  》 let myChart = this.$echarts.init(document.getElementById('myChart'))
        

      
        // 绘制图表
        myChart.setOption({
          title: {
            //  图 的 标题 
            text: '点赞量统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#555555'
            }
          },
          tooltip: {},
          
          
          //
          xAxis: {
            data: res.data.data.names,
            axisLabel: {
            interval: 0,  //设置间隔为0
            rotate: 25, //代表逆时针旋转25
          }
          },
          yAxis: {},

            //
          series: [{
            name: '点赞量',
            // 图的类型 。有  柱状图  等、、、
            type: 'bar',  
            data: res.data.data.values
          }]
        })
           
        })
        
       
    

    }
}
</script>


