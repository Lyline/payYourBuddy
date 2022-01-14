package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
  private final UserServiceImpl service;

  public RegisterController(UserServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/register")
  public String showRegisterForm(Model model){
    model.addAttribute("inscription",new RegisterDTO());
    return "/register";
  }

  @PostMapping("/register")
  public String processCreateUserForm(@ModelAttribute("inscription")RegisterDTO inscription,
                                      Model model){
    boolean response=service.createUser(inscription);

    if (response) {
      inscription.setValidation(true);
    }
    model.addAttribute("inscription",inscription);
    return "/register";
  }
}
