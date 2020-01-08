package com.honeacademy.webclientexample.model;

import lombok.Data;

@Data
public class Post {
	private String title;
	
	private String body;
	
	private Long userId;
	
	private Long id;
}
