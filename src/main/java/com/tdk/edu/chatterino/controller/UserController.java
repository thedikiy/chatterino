package com.tdk.edu.chatterino.controller;

import com.tdk.edu.chatterino.entity.User;
import com.tdk.edu.chatterino.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(path = "createUser")
  public ResponseEntity createUser(@Valid @RequestBody User user){
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

}
