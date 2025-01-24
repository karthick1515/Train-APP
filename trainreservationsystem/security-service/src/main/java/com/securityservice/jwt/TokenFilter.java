package com.securityservice.jwt;

import com.securityservice.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = null;
            String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
            }
            if (token != null && jwtUtils.validateToken(token)) {
                String username = jwtUtils.getUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        } catch (Exception e) {

            System.out.println(e.getClass() + " " + e.getMessage());

        }

        filterChain.doFilter(request, response);

    }
}


