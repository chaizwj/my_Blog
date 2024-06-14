package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.service.TypeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.blog.domain.Type;

import com.example.blog.dao.TypeDao;
import org.springframework.web.bind.annotation.RequestBody;


import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  分类服务实现类
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements TypeService {

    @Resource
    private TypeDao typeDao;



    public List<Type> getType() {
        return typeDao.getType();
    }

    @Override
    public void addType(@RequestBody Type type) {
        typeDao.insert(type);

    }

    @Override
    public void deleteType(int typeid) {
        QueryWrapper<Type> qw = new QueryWrapper<>();
        qw.eq("typeid",typeid);
        typeDao.delete(qw);
    }

    @Override
    public void updateType(Type type) {

        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("typeid",type.getTypeid());
        typeDao.update(type, qw);

    }
}
