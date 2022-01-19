package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 The profile controller allow display the user profile webpage.

 @version 0.1

 @see User
 @see UserDTO
 */
@Controller
@RequestMapping("/{id}")
public class ProfileController {

  private final UserServiceImpl service;

  private final ModelMapper mapper=new ModelMapper();

  public ProfileController(UserServiceImpl service) {
    this.service = service;
  }

  /**
   Displays the user profile webpage when the user is connected.

   @param id    The user id
   @param model The model interface
   @return      The user profile webpage
   */
  @GetMapping("/profile")
  public String showProfile(@PathVariable Long id, Model model){
    User user=service.getUser(id).get();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    model.addAttribute("user",userDTO);
    model.addAttribute("userInfo",user);
    return "profile";
  }
}
