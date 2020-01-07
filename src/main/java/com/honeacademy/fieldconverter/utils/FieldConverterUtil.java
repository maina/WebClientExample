package com.honeacademy.fieldconverter.utils;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FieldConverterUtil extends StdDeserializer<String>{

	protected FieldConverterUtil() {
		super(String.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	    String attribute = _parseString(p, ctxt);
		return DigestUtils.md5Hex(attribute);
	}

}
