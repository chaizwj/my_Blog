package com.example.blog.service.impl;

import com.example.blog.dao.SaveDao;
import com.example.blog.domain.Save;
import com.example.blog.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveServiceImpl implements SaveService {


    @Autowired
    SaveDao saveDao;
    @Override
    public Save ifExist(int uid, int blogid) {
        return saveDao.ifExist(uid, blogid);
    }

    @Override
    public int delete(int uid, int blogid) {
        return saveDao.delete(uid,blogid);
    }

    @Override
    public Integer insert_save_blogid_uid(int blogid, int uid) {
        return saveDao.insert_save_blogid_uid(blogid,uid);
    }
}
