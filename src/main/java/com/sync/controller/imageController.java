package com.sync.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sync.service.ImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "ImageService", description = "APIs for interacting with imgur API")
public class imageController {

	@Autowired
	ImageService  imageService;

	@PostMapping("/image")
	@ApiOperation(value = "Uploads the image to imgur system", response = Object.class)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,Authentication authentication) throws IOException
	{
		String username = authentication.getName();
		return ResponseEntity.ok(imageService.uploadImage(file,username));
	}

	@DeleteMapping("/image/{imageId}")
	@ApiOperation(value = "Delets the image from imgur system", response = Object.class)
	public ResponseEntity<?> deleteImage(@PathVariable String imageId,Authentication authentication) {

		String username = authentication.getName();
		return ResponseEntity.ok(imageService.deleteImage(imageId,username));
	
	}



}
