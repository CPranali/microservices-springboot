package com.pran.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Configuration
public class PetShopFallbackProvider implements FallbackProvider {
	
	private static final String DEFAULT_MESSAGE = "Pet Shop is not availbale";

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

		if (cause instanceof HystrixTimeoutException) {
            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
        } else {
            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
        }
	}

	@Override
	public String getRoute() {
		return "petshop-service";
	}

}
