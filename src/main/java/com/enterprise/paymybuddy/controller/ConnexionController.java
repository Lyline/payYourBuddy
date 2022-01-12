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

@Controller
public class ConnexionController {

  private final ConnexionServiceImpl service;
  ModelMapper mapper=new ModelMapper();

  public ConnexionController(ConnexionServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/")
  public String showConnectForm(Model model){
    model.addAttribute("logging",new UserConnexionDTO());
    return "connexion";
  }

  @PostMapping("/")
  public String processConnectForm(@ModelAttribute("logging") UserConnexionDTO userConnexion,
                                   Model model){
    User user=service.connect(userConnexion);

    if (user!=null){
      UserDTO userDTO=mapper.map(user,UserDTO.class);

      model.addAttribute("id",user.getUserId());
      model.addAttribute("user",userDTO);

      return "redirect:/"+user.getUserId();
    }
    return "errorConnexion";
  }
}
