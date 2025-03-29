package com.example.ms_security.util;

import com.example.ms_security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     final String authorizationHeader = request.getHeader("Authorization");
     String username = null;
     String jwtToken = null;

     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
          jwtToken = authorizationHeader.substring(7);
         username = jwtUtil.extractUsername(jwtToken);
     }

     if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
         UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
         if(jwtUtil.validateToken(jwtToken,userDetails)) {
             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
         }
     filterChain.doFilter(request, response);
    }
}
