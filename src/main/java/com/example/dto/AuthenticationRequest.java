package com.example.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
  
  // TODO - verify a suitable serial version UID
  private static final long serialVersionUID = 987654321L;

  private String username;

  private String password;

}
