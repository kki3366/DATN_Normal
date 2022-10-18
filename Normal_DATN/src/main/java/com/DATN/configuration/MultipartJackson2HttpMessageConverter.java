package com.DATN.configuration;

import java.lang.reflect.Type;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MultipartJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter{

	

	public MultipartJackson2HttpMessageConverter(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return super.canWrite(clazz, mediaType);
	}

	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return super.canWrite(type, clazz, mediaType);
	}

	@Override
	protected boolean canWrite(MediaType mediaType) {
		// TODO Auto-generated method stub
		return super.canWrite(mediaType);
	}

	
	
}
