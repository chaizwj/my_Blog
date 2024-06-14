package com.example.blog.service;

import com.example.blog.domain.Save;
import com.example.blog.domain.Thumbs;

public interface SaveService {


    Save ifExist(int uid, int blogid);

    int delete(int uid, int blogid);

    Object insert_save_blogid_uid(int blogid, int uid);
}
