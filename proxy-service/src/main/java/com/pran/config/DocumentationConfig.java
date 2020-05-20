package com.pran.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
		resources.add(SwaggerResource("petdetails-service","/petdetails/v2/api-docs","2.0"));
		resources.add(SwaggerResource("petshop-service","/petshop/v2/api-docs","2.0"));
		return resources;
	}

	private SwaggerResource SwaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
