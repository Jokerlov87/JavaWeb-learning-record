package com.creeper.service.impl;

import com.creeper.mapper.ModMapper;
import com.creeper.pojo.Mod;
import com.creeper.pojo.PageBean;
import com.creeper.service.ModService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModServiceImpl implements ModService {

    @Autowired
    private ModMapper modMapper;

//    @Override
//    public List<Mod> list() {
//        return modMapper.list();
//    }

    @Override
    public void delete(String modid) {
        modMapper.deleteById(modid);

    }

    @Override
    public void add(Mod mod) {

        modMapper.insert(mod);
    }

    @Override
    public void update(Mod mod) {
        modMapper.update(mod);
    }

    @Override
    public Mod getById(String modid) {
        return modMapper.getById(modid);
    }

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count=modMapper.count();
//
//        //获取分页查询结果列表
//        Integer start =(page-1)*pageSize;
//        List<Mod> modList =modMapper.page(start,pageSize);
//
//        //封装pagebean对象
//        PageBean pageBean =new PageBean(count,modList);
//
//        return pageBean;
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize,String modid,Double modversion, Boolean modavailable, String modinfo) {

        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Mod> suggestList =modMapper.list(modid, modversion, modavailable, modinfo);
        Page<Mod> p =(Page<Mod>) suggestList;

        //封装pagebean对象
        PageBean pageBean =new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }
}
