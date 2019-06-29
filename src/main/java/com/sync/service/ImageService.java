package com.sync.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sync.model.Image;
import com.sync.repository.ImageRepository;
import com.sync.util.ApplicationConstants;
import com.sync.vo.ImgurResponse;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Value("${imgur.clientid}")
	String clientID;
	
	
	@Value("${imgur.image.upload.url}")
	String uploadUrl;
	
	@Value("${imgur.image.delete.url}")
	String deleteUrl;
	
	@Autowired
	RestTemplate restTemplate;

	public String uploadImage(MultipartFile file, String username) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set(ApplicationConstants.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		headers.set(ApplicationConstants.AUTHORIZATION, clientID);

		String encodedfile = new String(Base64Utils.encode(file.getBytes()));
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add(ApplicationConstants.IMAGE, encodedfile);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<ImgurResponse> response = restTemplate.postForEntity(uploadUrl, requestEntity, ImgurResponse.class);
		
		ImgurResponse imgresponse = response.getBody();
		
		saveImage(imgresponse,username);
		
		return "Success";
		
	}

	private void saveImage(ImgurResponse imgresponse,String username) {
		Image img = new Image();
		img.setId(imgresponse.getData().getId());
		img.setUsername(username);
		img.setLink(imgresponse.getData().getLink());
		img.setDeletehash(imgresponse.getData().getDeletehash());
		imageRepository.save(img);
		
	}

	public String deleteImage(String imageId, String username) {
		
		Optional<Image> optional = imageRepository.findById(imageId);
		
		optional.ifPresent(img -> {
			HttpHeaders headers = new HttpHeaders();
			headers.set(ApplicationConstants.AUTHORIZATION, clientID);
			HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
			Map<String, String> uriVariables = new HashMap();
			uriVariables.put(ApplicationConstants.IMAGEHASH,img.getDeletehash());

			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject(deleteUrl, requestEntity, String.class, uriVariables);
			imageRepository.delete(img); 
		});
		
		
		return "Success";
	}
	

}
