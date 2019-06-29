package com.sync.vo;

import com.sync.model.Image;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImgurResponse {
	
	private Image data;

    private boolean success;

    private int status;


}
