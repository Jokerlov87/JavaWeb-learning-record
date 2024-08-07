package com.creeper.mapper;

import com.creeper.pojo.Mod;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Mapper
public interface ModMapper {
//    /*
//    查询全部mod数据
//     */
//    @Select("select * from table_mods")
//    List<Mod> list();

    /*
    根据id删除mod数据
     */
    @Delete("delete from table_mods where modid=#{modid}")
    void deleteById(String modid);

    /*
    添加mod数据
     */
    @Insert("insert into table_mods(modid,modversion,modavailable,modinfo) values (#{modid},#{modversion},#{modavailable},#{modinfo})")
    void insert(Mod mod);

    /*
    根据modid查询mod
     */
    @Select("select * from table_mods where modid = #{modid}")
    Mod getById(String modid);

    /*
    更新mod数据
     */
    //通过xml文件配置实现
    void update(Mod mod);

//    /*
//   查询总记录数
//    */
//    @Select("select count(*) from table_mods;")
//    public Long count();
//
//    /*
//    分页查询，获取列表数据
//     */
//    @Select("select * from table_mods limit #{start},#{pageSize}")
//    public List<Mod> page(Integer start,Integer pageSize);

    /*
    mod信息查询
    */
    //@Select("select * from table_mods")
    public List<Mod> list(String modid,Double modversion, Boolean modavailable, String modinfo);
}
