package com.example.api_gateway.filter;

import com.example.api_gateway.exception.UnauthorizedException;
import com.example.api_gateway.util.PathMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private final PathMatcher pathMatcher;

    @Autowired
    public RouteValidator(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/register",
            "/api/auth/login"
    );

    private final Map<String, List<String>> routes= Map.of(
            "MANAGER", List.of("/api/employee/**","/api/department/**"),
            "STAFF", List.of("/api/department/departmentCode/**")
    );

    public void validateRoute(ServerWebExchange exchange, String role) {
          ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getURI().getPath());
          List<String> endpoints=routes.get(role);
        if (endpoints == null) {
            throw new UnauthorizedException("Unauthorized: Role not found or has no access to any endpoints.");
        }
          List<Boolean> isMatched=new ArrayList<>();
        for (String endpoint : endpoints) {
            boolean match = pathMatcher.match(endpoint, request.getURI().getPath());
            System.out.println("Comparing: " + request.getURI().getPath() + " with " + endpoint);
            System.out.println("Match result: " + match);
            isMatched.add(match);
        }


        if(!isMatched.contains(true)){
              throw new UnauthorizedException("Unauthorized");
          }
    }

    public Predicate<ServerHttpRequest> isSecured=
            request->openApiEndpoints.stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));



}
