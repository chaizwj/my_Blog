//  vue.config.js是个配置项文件，文件名固定。这些key都是固定项

module.exports = {

    devServer: {


        // 本机的 node.js 的端口是 81  
        //  ******  0.0.0.0 也就是任何人都能访问 
        host: '0.0.0.0',
        // *******
        port: 8081,

        //以上的ip和端口是我们本机的，node.js帮我们代理去访问后台端口

        // 下面为需要跨域的
        proxy: { //配置跨域
            '/': { 
                // *********  只有配置上传服务器时候要修改成 http://120.79.142.79:8098 请求服务机器其的ip的接口地址
                    // target:'http://120.79.142.79:8098',
                target: 'http://localhost:8098:', //这里后台的地址模拟的;应该填写你们真实的后台接口
                // **********
                ws: false,
                changOrigin: true, //允许跨域
                pathRewrite: {
                    '^/': '/'
                }
            }

        }
    }

}