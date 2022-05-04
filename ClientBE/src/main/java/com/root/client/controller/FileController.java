package com.root.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.root.client.model.FileUpdateInfoModel;
import com.root.client.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;
	
	@GetMapping("/list")
	public List<String> getFileNames() {
		
		return fileService.getFileNameList();
	}

	@GetMapping("/{fileName}/{pageIndex}/{totalPages}")
	public Map<String, List> getFileData(@PathVariable("fileName") String fileName, 
			@PathVariable("pageIndex") int pageIndex, @PathVariable("totalPages") int totalPages) {
		
		Map<String,List> map = new HashMap<>();
		map.put(fileName, fileService.fileRead(fileName, pageIndex, totalPages));
		//return fileService.fileRead(fileName, pageIndex, totalPages);
		return map;
	}
	
	@PostMapping(value="/update", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public void updateFile(@RequestBody FileUpdateInfoModel fileUpdateObj) {
		System.out.println("update: "+fileUpdateObj);
		//fileService.updateFile(fileUpdateObj);
	}
}
