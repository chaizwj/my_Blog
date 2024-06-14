package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication(scanBasePackages = "com.example.blog")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
    // 静态代码块。设置不出现那个 日志输出。
    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }



}
