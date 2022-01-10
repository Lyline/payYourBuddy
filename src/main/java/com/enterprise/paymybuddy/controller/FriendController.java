package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.FriendConnexion;
import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendController {

  private final UserServiceImpl service;

  public FriendController(UserServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/{id}/add_friend")
  public String showFriendForm(@PathVariable Long id,
                               Model model){

    User user=service.getUser(id).get();

    ModelMapper mapper=new ModelMapper();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    model.addAttribute("user", userDTO);
    model.addAttribute("connexion", new FriendConnexion());

    return "friendConnexion";
  }

  @PostMapping("/{id}/add_friend")
  public String createFriendConnexion(@PathVariable Long id,
                                      @ModelAttribute("connexion")FriendConnexion friendConnexion,
                                      Model model){
    User user=service.getUser(id).get();

    ModelMapper mapper=new ModelMapper();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    boolean result=service.createFriend(user,friendConnexion);

    if (result){friendConnexion.setValidation(true);}

    model.addAttribute("user",userDTO);
    model.addAttribute("connexion", friendConnexion);

    return "friendConnexion";
  }
}
