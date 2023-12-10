package com.example.security;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key:3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMiliseconds = 3600000;

  @Autowired
  private UserDetailsService userDetailsService;
  
  /**
   * Create token for injecting in the authentication filter
   * @param username user name
   * @param roles list of roles of user
   * @return security token
   */
  public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMiliseconds);

    return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
           .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  // TODO - pass the actual password in second argument ?
  /**
   * Obtain an authentication object in Spring Security to represent a successful authentication 
   * or to establish  the identity of a principal (user) once the user has been authenticated
   * @param token security token
   * @return authentication request token
   */
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, StringUtils.EMPTY, userDetails.getAuthorities());
  }

  /**
   * Obtain user name from a security token
   * @param token security token
   * @return user name
   */
  private String getUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * Get the shared secret or private key 
   * @return security key
   */
  private Key getSigningKey() {
    // TODO - key is in hex, not base64
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  /**
   * Extract security token from an http request
   * @param req http request
   * @return token as tring, null if request contains no bearer token
   */
  public String extractToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

  /**
   * Validate if token is genuine and has not expired at the time of call
   * @param token security token
   * @return true if token is valid, false otherwise
   */
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
      boolean tokenExpired = claims.getBody().getExpiration().before(new Date());
      return !tokenExpired;
    } catch (Exception e) {
      // TODO - check if returning false or throw an exception here is better
      return false;
    }
  }
}
