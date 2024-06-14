package com.example.blog.util;

import java.util.HashMap;
import java.util.Map;

public class DataUtil {

//    针对 柱状图  的颜色控制
    public static Map<String,String> createItemStyle(Integer sale){

        String color = "";
        if(sale<25){
            color = "#96dee8";
        }
        if(sale>25 && sale<50){
            color = "#c4ebad";
        }
        if(sale>50 && sale<75){
            color = "#6be6c1";
        }
        if(sale>75 && sale<100){
            color = "#3fb1e3";
        }
        if(sale>100 && sale<125){
            color = "#a0a7e6";
        }
        if(sale>125){
            color = "#00FFFF";
        }

        Map<String,String> map = new HashMap<>();
        map.put("color", color);
        return map;

    }



}