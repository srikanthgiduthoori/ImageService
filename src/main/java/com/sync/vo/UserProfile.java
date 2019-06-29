package com.sync.vo;

import java.util.List;

import com.sync.model.Image;
import com.sync.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserProfile {
	
	private User user;
	private List<Image> images;

}
