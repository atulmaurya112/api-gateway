package com.ms.conversion.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(a -> a.path("/get").filters(b -> b.addRequestHeader("CustomHeader", "MyHeader")).uri("https://github.com/in28minutes/spring-microservices-v2/blob/main/03.microservices/01-step-by-step-changes/microservices-v2-1.md#spring-cloud-api-gateway---step-22-to-step-25"))
			.route(a -> a.path("/currency-exchange/**").uri("lb://currency-exchange"))
			.route(a -> a.path("/currency-conversion/**").uri("lb://currency-conversion"))
			.route(a -> a.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
			.route(a -> a.path("/currency-conversion-new/**").filters(b -> b.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}")).uri("lb://currency-conversion"))
		.build();
	}
	
}
