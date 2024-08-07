package com.creeper.service;

import com.creeper.pojo.Mod;
import com.creeper.pojo.PageBean;

import java.util.List;

public interface ModService {
//    /*
//    查询全部mod数据
//     */
//    List<Mod> list();


    /*
    删除mod数据
     */
    void delete(String modid);

    /*
    新增mod
     */
    void add(Mod mod);

    /*
    更新mod
     */
    void update(Mod mod);

    /*
    根据modid查询mod数据
     */
    Mod getById(String modid);

    /*
    分页查询
     */
    PageBean page(Integer page, Integer pageSize,String modid,Double modversion, Boolean modavailable, String modinfo);
}
