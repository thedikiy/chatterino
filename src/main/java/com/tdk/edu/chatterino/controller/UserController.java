package com.tdk.edu.chatterino.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/user")
public class UserController {

  @GetMapping(path = "me")
  @ResponseBody
  public Object getUser(OAuth2Authentication principal){
    return principal.getUserAuthentication().getDetails();
  }

}
