package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.ConnexionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 The connexion controller allow to display the connexion form and connect the user to the application.

 @version 0.1

 @see UserConnexionDTO
 @see User
 */
@Controller
public class ConnexionController {

  private final ConnexionServiceImpl service;
  private final ModelMapper mapper=new ModelMapper();

  public ConnexionController(ConnexionServiceImpl service) {
    this.service = service;
  }

  /**
   Display the connexion form webpage.

   @param model The model interface
   @return      The connexion form webpage
   */
  @GetMapping("/")
  public String showConnectForm(Model model){
    model.addAttribute("logging",new UserConnexionDTO());
    return "connexion";
  }

  /**
   Posts the connexion form and redirect to user domain if the connexion is validated else display a connexion webpage
   with an error message.

   @param userConnexion The connexion attributes : email, password and validation statement
   @param model         The model interface
   @return              The user's homepage if the connexion is validated, else connexion webpage with an error message
   */
  @PostMapping("/")
  public String processConnectForm(@ModelAttribute("logging") UserConnexionDTO userConnexion,
                                   Model model){
    User user=service.connect(userConnexion);

    if (user!=null){
      UserDTO userDTO=mapper.map(user,UserDTO.class);
      userConnexion.setConnected(true);
      model.addAttribute("id",user.getUserId());
      model.addAttribute("user",userDTO);

      return "redirect:/"+user.getUserId();
    }

    model.addAttribute("logging",userConnexion);
    return "connexion";
  }
}
