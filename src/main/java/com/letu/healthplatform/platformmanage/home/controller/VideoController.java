package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.Video;
import com.letu.healthplatform.platformmanage.home.service.VideoService;

@RestController
@RequestMapping("/video")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(Video video,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return videoService.selectByParam(video, page, pageSize);
	}
	
	@PostMapping(value="/insertVideo")
	public  Object  insertVideo(Video video){
		return videoService.insert(video);
	}

	@PostMapping(value="/updateVideo")
    public Object  updateVideo(Video video){
		return videoService.updateByPrimaryKey(video);
    }
}
