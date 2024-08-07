package com.creeper.mapper;

import com.creeper.pojo.Suggest;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface SuggestMapper {
    /*
    查询全部suggest数据
     */
    @Select("select * from table_suggest")
    List<Suggest> getSuggestAll();

    /*
    根据id删除suggest
     */
    @Delete("delete from table_suggest where suggestid = #{id}")
    int deleteById(Integer suggestid);

    /*
    新增suggest
     */
    @Insert("insert into table_suggest(username,modid,suggest)values (#{username},#{modid},#{suggest})")
    int create(Suggest suggest);

    /*
    根据suggestid查询suggest
     */
    @Select("select * from table_suggest where suggestid =#{suggestid}")
    Suggest getById(Integer suggestid);

    /*
    更新suggest
     */
    @Update("UPDATE table_suggest SET username=#{username}, modid=#{modid}, suggest=#{suggest} WHERE suggestid=#{suggestid}")
    int update(Suggest suggest);

}
