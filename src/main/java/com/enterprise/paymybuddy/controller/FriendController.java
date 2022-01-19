package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.FriendConnexionDTO;
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

/**
 The friend controller allow display the friend connexion form and link the user with another users.

 @version 0.1

 @see User
 @see FriendConnexionDTO
 */
@Controller
public class FriendController {

  private final UserServiceImpl service;

  public FriendController(UserServiceImpl service) {
    this.service = service;
  }

  /**
   Displays the friend connexion form.

   @param id    The user id
   @param model The model interface
   @return      The friend connexion form webpage
   */
  @GetMapping("/{id}/add_friend")
  public String showFriendForm(@PathVariable Long id,
                               Model model){

    User user=service.getUser(id).get();

    ModelMapper mapper=new ModelMapper();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    model.addAttribute("user", userDTO);
    model.addAttribute("connexion", new FriendConnexionDTO());

    return "friendConnexion";
  }

  /**
   Posts the friend connexion form, if the form is validated the new friend is added to the friend list of user.

   @param id                  The user id
   @param friendConnexionDTO  The friend connexion attributes : email and validation statement
   @param model               The model interface
   @return                    The friend form webpage with a validation/error message
   */
  @PostMapping("/{id}/add_friend")
  public String createFriendConnexion(@PathVariable Long id,
                                      @ModelAttribute("connexion") FriendConnexionDTO friendConnexionDTO,
                                      Model model){
    User user=service.getUser(id).get();

    ModelMapper mapper=new ModelMapper();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    boolean result=service.createFriend(user, friendConnexionDTO);

    if (result){
      friendConnexionDTO.setValidated(true);}

    model.addAttribute("user",userDTO);
    model.addAttribute("connexion", friendConnexionDTO);

    return "friendConnexion";
  }
}
