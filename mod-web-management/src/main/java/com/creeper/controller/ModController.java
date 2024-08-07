package com.creeper.controller;

import com.creeper.pojo.Mod;
import com.creeper.pojo.PageBean;
import com.creeper.pojo.Result;
import com.creeper.service.ModService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mods")
public class ModController {

    @Autowired
    private ModService modService;

//    /*
//    查询全部mod数据
//     */
//    @GetMapping
//    public Result list(){
//        log.info("查询全部mod数据");
//
//        //调用service查询mod数据
//        List<Mod> modList = modService.list();
//       return Result.success(modList);
//    }

    /*
    删除mod数据
     */
    @DeleteMapping("/{modid}")
    public Result delete(@PathVariable String modid){
        log.info("根据modid删除mod:{}",modid);
        //调用service删除mod数据
        modService.delete(modid);
        return Result.success();

    }

    /*
    新增mod数据
     */
    @PostMapping
    public Result add(@RequestBody Mod mod){
        log.info("新增mod：{}",mod);
        //调用service新增mod
        modService.add(mod);
        return Result.success();

    }

    /*
    根据modid查询mod数据
     */
    @GetMapping("/{modid}")
    public Result getById(@PathVariable String modid){
        log.info("根据modid查询mod数据,modid:{}",modid);
        Mod mod = modService.getById(modid);
        return Result.success(mod);

    }

    /*
    修改mod数据
     */
    @PutMapping
    public Result update(@RequestBody Mod mod){
        log.info("更新mod数据：{}",mod);
        modService.update(mod);
        return Result.success();

    }

    /*
    分页条件查询
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String modid,Double modversion, Boolean modavailable, String modinfo) {
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,modid,modversion,modavailable,modinfo);
        //调用service分页查询
        PageBean pageBean = modService.page(page, pageSize,modid,modversion,modavailable,modinfo);
        return Result.success(pageBean);
    }
}
