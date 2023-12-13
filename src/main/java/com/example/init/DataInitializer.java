package com.example.init;

import com.example.models.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // User admin = new User("admin2", "admin@example.com", this.passwordEncoder.encode("password"),
    //     Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
    // userService.addUser(admin);

    // User normal = new User("test2", "test@example.com", this.passwordEncoder.encode("random"),
    //     Arrays.asList("ROLE_USER"));
    // userService.addUser(normal);
  }
}
