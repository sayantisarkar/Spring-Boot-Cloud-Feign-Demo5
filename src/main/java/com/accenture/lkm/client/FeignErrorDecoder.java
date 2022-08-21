package com.accenture.lkm.client;

import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;
@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == 500) {
			return new RuntimeException("Employee with given id doesnt exists");
		}
		return null;
	}
}