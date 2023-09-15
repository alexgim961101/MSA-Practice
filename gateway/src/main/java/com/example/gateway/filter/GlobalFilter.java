package com.example.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // pre filter 영역
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            request.mutate().header("service", "service-header");
            log.info("Global Filter baseMessage -> {}", config.baseMessage);

            if(config.isPreLogger()) log.info("Global Filter Start : request id -> {}", request.getId());

            // post filter 영역
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                response.getHeaders().add("service", "service-header");
                if(config.isPostLogger()) log.info("Global Filter end : response id -> {}", response.getStatusCode());
            }));
        }));
    }

    @Data
    static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
