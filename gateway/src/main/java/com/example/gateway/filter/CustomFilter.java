package com.example.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter filter = new OrderedGatewayFilter(((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            request.mutate().header("service", "service-header");
            log.info("Custom Filter baseMessage -> {}", config.getBaseMessage());
            log.info("Custom pre filter : request id -> {}", request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                response.getHeaders().add("service", "service-header");
                log.info("Custom Post filters : response id -> {}", response.getStatusCode());
            }));

        }), 0);

        return filter;
    }

    @Data
    static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
