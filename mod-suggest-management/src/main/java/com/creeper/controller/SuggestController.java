package com.creeper.controller;

import com.creeper.anno.Log;
import com.creeper.apiResponse.ApiResponse;
import com.creeper.pojo.PageBean;
import com.creeper.pojo.Suggest;
import com.creeper.service.SuggestService;
import jakarta.validation.Valid;
import lombok.Locked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/suggest")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    /*
     * 处理新增 suggest 的请求
     * POST 请求，路径为 /suggest
     * @param suggest 要添加的 suggest 对象
     * @return ResponseEntity<ApiResponse<Suggest>> 响应信息
     */
    @Log
    @PostMapping
    public ResponseEntity<ApiResponse<Suggest>> create(@RequestBody @Valid Suggest suggest) {
        log.info("Received POST request to add a suggest with id: {}", suggest.getSuggest());
        if (suggestService.getById(suggest.getSuggestid()) != null){
            log.warn("Attempted to create an existing suggest with id: {}", suggest.getSuggest());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(409, "Suggest already exists", null));
        }
        if (suggestService.create(suggest)){
            log.info("Successfully created suggest with id: {}", suggest.getSuggestid());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(201, "Suggest created successfully", suggest));
        }
        log.error("Failed to create suggest with id: {}", suggest.getSuggestid());
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Failed to create Suggest", null));
    }

    /*
     * 处理删除 suggest 数据的请求
     * DELETE 请求，路径为 /suggest/{suggestid}
     * @param suggestid 要删除的 suggest 的 ID
     * @return ResponseEntity<String> 响应信息
     */
    @Log
    @DeleteMapping("/{suggestid}")
    public ResponseEntity<String> delete(@PathVariable Integer suggestid) {
        log.info("Received DELETE request for suggest with id: {}", suggestid);
        if (suggestService.delete(suggestid)){
            log.info("Successfully deleted suggest with id: {}", suggestid);
            return ResponseEntity.ok("Delete successfully");
        }
        log.warn("Failed to delete suggest with id: {}", suggestid);
        return ResponseEntity.status(404).body("suggest not found, delete failed");
    }

    /*
     * 处理更新 suggest 数据的请求
     * PUT 请求，路径为 /suggest/{suggestid}
     * @param suggestid 要更新的 suggest 的 ID
     * @param suggest 更新后的 suggest 对象
     * @return ResponseEntity<ApiResponse<Suggest>> 响应信息
     */
    @Log
    @PutMapping("/{suggestid}")
    public ResponseEntity<ApiResponse<Suggest>> update(@PathVariable Integer suggestid, @RequestBody @Valid Suggest suggest) {
        log.info("Received PUT request to update suggest with id: {}", suggestid);

        if (suggestService.getById(suggestid) == null){
            log.warn("Attempted to update non-existent suggest with id: {}", suggestid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "Mod not found, update failed", null));
        }
        if (suggestService.update(suggest)){
            log.info("Successfully updated suggest with id: {}", suggestid);
            return ResponseEntity.ok(new ApiResponse<>(200, "Update successful", suggest));
        }
        log.error("Failed to update suggest with id: {}", suggestid);
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Request format error, update failed", null));
    }

    /*
     * 根据 suggestid 查询 suggest 的请求处理器
     * GET 请求，路径为 /suggest/{suggestid}
     * @param suggestid 要查询的 suggest 的 ID
     * @return ResponseEntity<ApiResponse<Suggest>> 响应信息
     */
    @Log
    @GetMapping("/{suggestid}")
    public ResponseEntity<ApiResponse<Suggest>> getSuggest(@PathVariable Integer suggestid) {
        log.info("Received GET request for suggest with id: {}", suggestid);

        if (suggestService.getById(suggestid) == null){
            log.warn("Attempted to retrieve non-existent suggest with id: {}", suggestid);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "suggest not found", null));

        }
        Suggest suggest = suggestService.getById(suggestid);
        log.info("Retrieved suggest with id: {}", suggestid);
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", suggest));
    }

    /*
     * 获取所有 suggest 信息的请求处理器
     * GET 请求，路径为 /suggest/all
     * @return ResponseEntity<ApiResponse<List<Suggest>>> 响应信息
     */
    @Log
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Suggest>>> getSuggestAll(){
        log.info("Received GET request for all suggest");

        List<Suggest> suggestList = suggestService.getSuggestAll();
        log.info("Found all suggest");
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", suggestList));
    }
}
