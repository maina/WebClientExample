package com.honeacademy.fieldconverter.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.honeacademy.fieldconverter.utils.FieldConverterUtil;

import lombok.Data;

@Data
public class Customer {
	private String firstName;
	
	private String lastName;
	
	@JsonDeserialize(using = FieldConverterUtil.class)
	private String email;
}
