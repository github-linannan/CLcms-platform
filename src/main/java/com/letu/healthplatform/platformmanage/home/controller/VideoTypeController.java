package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.VideoType;
import com.letu.healthplatform.platformmanage.home.service.VideoTypeService;

@RestController
@RequestMapping("/videoType")
public class VideoTypeController {
	@Autowired
	private VideoTypeService videoTypeService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(VideoType videoType,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return videoTypeService.selectByParam(videoType, page, pageSize);
	}
	
	@PostMapping(value="/insertVideoType")
	public  Object  insertVideoType(VideoType videoType){
		return videoTypeService.insert(videoType);
	}

	@PostMapping(value="/updateVideoType")
    public Object  updateVdieoType(VideoType videoType){
		return videoTypeService.updateByPrimaryKey(videoType);
    }
	@GetMapping(value="/delVideoType/{id}")
	public Object  delVideoType(@PathVariable String id){
		return videoTypeService.delVideoTypeByPrimaryKey(id);
	}
}
