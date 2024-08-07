package com.creeper.service;

import com.creeper.pojo.PageBean;
import com.creeper.pojo.Suggest;

import java.util.List;

public interface SuggestService {
    /*
    查询全部suggest数据
     */
    List<Suggest> getSuggestAll();

    /*
    删除suggest
     */
    boolean delete(Integer suggestid);

    /*
    新增suggest
     */
    boolean create(Suggest suggest);

    /*
    根据suggestid查询suggest
     */
    Suggest getById(Integer suggestid);

    /*
    更新suggest
     */
    boolean update(Suggest suggest);

}
