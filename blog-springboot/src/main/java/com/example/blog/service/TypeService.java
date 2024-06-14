package com.example.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.domain.Type;
import java.util.List;

/**
 * <p>
 * 分类服务类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
public interface TypeService extends IService<Type> {



    /**
     * 获取每个分类的博客数量
     * @return list
     */
    List<Type> getType();

    void addType(Type type);

    void deleteType(int typeid);

    void updateType(Type type);
}
