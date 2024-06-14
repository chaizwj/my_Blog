package com.example.blog.util;

public class WordsFilter {


    public static String Filter(String words){


        String words2 ;
        String words3 ;
        String words4 ;
        String words5 ;
        String words6 ;

        if (words.contains("操")||words.contains("妈的")||words.contains("傻逼")||words.contains("脑残")||words.contains("sb"))
        {


             words2 = words.replace("操", "*");
             words3 = words2.replace("你妈", "**");
             words4 = words3.replace("傻逼", "**");
             words5 = words4.replace("脑残", "**");
             words6 = words5.replace("sb", "**");

        }
        else
        {
            words6 = words;
        }

        return words6;
    }
}
