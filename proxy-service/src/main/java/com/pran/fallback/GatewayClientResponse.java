package com.pran.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

public class GatewayClientResponse implements ClientHttpResponse {
	
	private HttpStatus status;
    private String message;
    

	public GatewayClientResponse(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public InputStream getBody() throws IOException {
		return new ByteArrayInputStream(message.getBytes());
	}

	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
	}

	@Override
	public void close() {
	}

	@Override
	public int getRawStatusCode() throws IOException {
		return status.value();
	}

	@Override
	public HttpStatus getStatusCode() throws IOException {
		return status;
	}

	@Override
	public String getStatusText() throws IOException {
		return status.getReasonPhrase();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}