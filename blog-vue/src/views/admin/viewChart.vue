<template>
  
  
      <!-- 2. 为ECharts准备一个具备大小（宽高）的Dom -->
     <div id="myChart" :style="{width: '800px', height: '600px',marginLeft:'350px'}"></div>
  

</template>





<script>
export default {

mounted (){
     
     
         
        this.$axios.get('/echart/barVOListView').then(res=>{ 

            console.log(res.data.data.values)
          // 必须这么写。   下面两行代码。
          const echarts = require('echarts')
          // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('myChart'))

          //   这么写不行的。（可能和 Vue 的this指向有关系） - - - -》 let myChart = this.$echarts.init(document.getElementById('myChart'))
        

      
        // 绘制图表
        myChart.setOption({
          title: {
            //  图表 的 标题 
            text: '浏览量统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#555555'
            }
          },

          tooltip: {},
          // x轴 就是显示每个柱子下面的文字。赋值给data数组。（这里是 博客的标题的字符串数组）
          xAxis: {
            data: res.data.data.names,

            axisLabel: {
            interval: 0, //设置每个柱子之间的间隔为0
            rotate: 25, //代表 x轴的 下面的文字。逆时针旋转25（不然 文字 太长会重叠）
          }
          },
          yAxis: {},


            // series 属性，即每一根柱子 的显示 对应数值。
          series: [{
            name: '浏览量',
            // 图的类型 ： 柱状图、饼图 等。type 属性进行赋值 比如：pie、bar 
            type: 'bar',  
            //    赋值给data 数组，整形数组，里面包含了每个博客的views数
            data: res.data.data.values
          }]
        })
           
        })
        
       
    

    }
}
</script>


