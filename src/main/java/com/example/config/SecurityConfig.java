package com.example.config;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.security.JwtTokenFilter;

import io.jsonwebtoken.security.SecurityException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  // TODO - verify if need jwtTokenProvider for injection
  @Autowired
  private JwtTokenFilter jwtTokenFilter;

  @Bean
  public AuthenticationManager authenManager(AuthenticationConfiguration authenConfig) throws AuthenticationException {
    try {
      return authenConfig.getAuthenticationManager();
    } catch (Exception e) {
      throw new AuthenticationException("Error in SecurityConfig, unable to get authentication manager");
    }
  }

  @Bean
  public SecurityFilterChain secureFilterChain(HttpSecurity httpSecure) {
    try {
      httpSecure.csrf(csrf -> csrf.disable())
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(auth -> {
            try {
              auth.requestMatchers("/auth/signin").permitAll().anyRequest().authenticated();
            } catch (Exception e) {
              // TODO - change type of exception?
              throw new RuntimeException("Error encountered while authorizing http request");
            }
          });
      httpSecure.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
      return httpSecure.build();
    } catch (Exception e) {
      throw new SecurityException("Error while getting security filter chain");
    }
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/v3/api-docs", "/v3/api-docs/**", "/configuration/ui",
        "/swagger-resources/**", "/configuration/security", "/swagger-ui.html",
        "/swagger-ui/**", "/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config",
        "/webjars/**", "/web/**", "/projects/*", "/users/*", "/tasks/*");
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.applyPermitDefaultValues();
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }
}
