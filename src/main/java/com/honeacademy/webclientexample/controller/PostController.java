package com.honeacademy.webclientexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.honeacademy.webclientexample.model.Post;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class PostController {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Post> createPost(@RequestBody final Post request) {
		return Mono.just(request);
	}

}
