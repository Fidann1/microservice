package com.example.api_gateway.util;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;


@Component
public class PathMatcher {

    private static final AntPathMatcher matcher = new AntPathMatcher();

    public Boolean match(String pattern, String path) {
        return matcher.match(pattern, path);
    }
}
