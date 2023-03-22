package com.pxy.ggkt.vod.controller;


import com.pxy.ggkt.model.vod.Chapter;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vo.vod.ChapterVo;
import com.pxy.ggkt.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/admin/vod/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //大纲列表
    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId) {
       List<ChapterVo> list  =chapterService.getTreeList(courseId);
        return Result.ok(list);
    }

    //添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok(null);
    }
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    //删除章节
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        chapterService.removeById(id);
        return Result.ok(null);
    }



}

