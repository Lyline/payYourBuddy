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

@Controller
@RequestMapping("/{id}")
public class ProfileController {

  private final UserServiceImpl service;

  private ModelMapper mapper=new ModelMapper();

  public ProfileController(UserServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/profile")
  public String showProfile(Model model, @PathVariable Long id){
    User user=service.getUser(id).get();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    model.addAttribute("user",userDTO);
    model.addAttribute("userInfo",user);
    return "profile";
  }
}
