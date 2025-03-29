package com.example.api_gateway.filter;

import com.example.api_gateway.exception.UnauthorizedException;
import com.example.api_gateway.util.JwtUtil;
import com.netflix.spectator.impl.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final RouteValidator routeValidator;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(JwtUtil jwtUtil, RouteValidator routeValidator) {
       super(Config.class);
        this.jwtUtil = jwtUtil;
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return((exchange, chain) -> {
            String token=null;
            String username=null;
            String role=null;
            ServerWebExchange modifiedExchange = null;

            if(routeValidator.isSecured.test((ServerHttpRequest) exchange.getRequest())){
                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                   token=authHeader.substring(7);
                   username=jwtUtil.extractUsername(token);
                   role=jwtUtil.extractRole(token);
                    modifiedExchange = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                    .header("username", username).build())
                            .build();


                }
                if(jwtUtil.isTokenExpired(token)){
                    throw new UnauthorizedException("Token is expired");
                }
                routeValidator.validateRoute(exchange,role);
                return chain.filter(modifiedExchange);
            }
           return chain.filter(exchange);

        });
    }

    public static class Config {

    }
}
