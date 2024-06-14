<template>
    <div id="myChart" :style="{width: '800px', height: '600px',marginLeft:'350px'}"></div>
</template>
<script>
    export default {
        name: 'Pie',
        mounted(){
         
             this.$axios.get('/echart/sumPie').then(res=>{ 
                 // 必须这么写。   下面两行代码。
          const echarts = require('echarts')
          // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('myChart'))
                // 绘制图表
                myChart.setOption({
                   
                   tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '5%',
    left: 'center'
  },
  series: [
    {
      name: '',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 40,
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: res.data.data
    }
  ]
                });
            })
        }
    }
</script>