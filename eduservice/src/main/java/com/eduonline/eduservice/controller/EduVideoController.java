package com.eduonline.eduservice.controller;


import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduVideo;
import com.eduonline.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    EduVideoService videoService;

    /**
     * 添加小节
     * @param video
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        boolean flag =  videoService.addVideo(video);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 根据小节id删除小节信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        boolean flag =  videoService.deleteVideo(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     *修改小节信息
     * @param video
     * @return
     */
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        boolean flag = videoService.updateVideo(video);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("getVideoInfoById/{id}")
    public R getVideoInfoById(@PathVariable String id){
        EduVideo video = videoService.getVideoByVideoId(id);
        if(video!=null){
            return R.ok().data("items",video);
        }else {
            return R.error().message("该小节不存在！");
        }

    }
}

