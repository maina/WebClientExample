package com.honeacademy.fieldconverter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.honeacademy.fieldconverter.model.Customer;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Customer> createBuyer(@RequestBody final Customer request) {
		return Mono.just(request);
	}

}
