package com.example.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter filter = new OrderedGatewayFilter(((exchange, chain) -> {
            // preFilter 영역
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            request.mutate().header("logging-service", "logging-service-header");
            if(config.isPreFilter()) log.info("Logging pre Filter Start : request id -> {}", request.getId());

            // postFilter 영역
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                response.getHeaders().add("logging-service", "logging-service-header");
                if(config.isPostFilter()) log.info("Logging pre Filter end : response id -> {}", response.getStatusCode());
            }));
        }), Ordered.LOWEST_PRECEDENCE);

        return filter;
    }

    @Data
    static class Config {
        private String baseMessage;
        private boolean preFilter;
        private boolean postFilter;
    }
}
