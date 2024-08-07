package com.creeper.service.impl;

import com.creeper.mapper.SuggestMapper;
import com.creeper.pojo.PageBean;
import com.creeper.pojo.Suggest;
import com.creeper.service.SuggestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SuggestMapper suggestMapper;

    @Override
    public List<Suggest> getSuggestAll() {
        return suggestMapper.getSuggestAll();
    }

    @Override
    public boolean delete(Integer suggestid) {
        if(suggestMapper.deleteById(suggestid)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Suggest suggest) {
        if(suggestMapper.create(suggest)>0){
            return true;
        }
        return false;
    }

    @Override
    public Suggest getById(Integer suggestid) {
        return suggestMapper.getById(suggestid);
    }

    @Override
    public boolean update(Suggest suggest) {
        if(suggestMapper.update(suggest) > 0){
            return true;
        }
        return false;
    }
}
