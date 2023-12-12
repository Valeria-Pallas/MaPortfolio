package com.example.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.AuthenticationRequest;
import com.example.repository.AuthenUserRepository;
import com.example.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/signin")
public class AuthenController {

  @Autowired
  private AuthenticationManager authenManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  // TODO - verify if private UserRepository userRepo or as below
  @Autowired
  private AuthenUserRepository authenUserRepo;

  // TODO - formData -> authenData
  /**
   * Sign in using the authentication data that was obtained from the request body
   * @param formData authentication data
   * @return a response entity with its body as a map containing keys "username" and "token"
   * throw BadCredentialsException if AuthenticationException error found
   */
  @Operation(description = "Authenticate user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "signin", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))
    })
  })
  @PostMapping("/signin")
  public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest formData) {
    try {
      String username = formData.getUsername();
      
      authenManager.authenticate(new UsernamePasswordAuthenticationToken(username, formData.getPassword()));
      String token = jwtTokenProvider.createToken(username, authenUserRepo.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User name " + username + " not found"))
        .getRoles());

      Map<Object, Object> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return ok(model);
    } catch (AuthenticationException e) {
      // TODO - return http error code instead (?)
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
