package com.example.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenFilter extends GenericFilterBean {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.extractToken((HttpServletRequest) req);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(token));
    }
    filterChain.doFilter(req, res);
  }
  
}
