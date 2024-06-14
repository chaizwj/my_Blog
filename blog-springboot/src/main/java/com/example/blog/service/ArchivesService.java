package com.example.blog.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.domain.Blog;

import java.util.List;

public interface ArchivesService {
    /**
     * 博客归档数据
     * @return
     */
    List<Blog> getArchivesList();
}
