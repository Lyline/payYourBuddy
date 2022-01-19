package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 The register controller allow display creation user form and create a new user.

 @version 0.1

 @see RegisterDTO
 */
@Controller
public class RegisterController {
  private final UserServiceImpl service;

  public RegisterController(UserServiceImpl service) {
    this.service = service;
  }

  /**
   Displays the registration form webpage.

   @param model The model interface
   @return      The registration form
   */
  @GetMapping("/register")
  public String showRegisterForm(Model model){
    model.addAttribute("inscription",new RegisterDTO());
    return "/register";
  }

  /**
   Posts the registration form, if it's validated display a validation creation message, else display an error message.

   @param inscription The inscription attributes : First name, last name, email, password, bank account number, validation
   statement
   @param model The model interface
   @return  The registration webpage with a validation/error message
   */
  @PostMapping("/register")
  public String processCreateUserForm(@ModelAttribute("inscription")RegisterDTO inscription,
                                      Model model){
    boolean response=service.createUser(inscription);

    if (response) {
      inscription.setValidated(true);
    }
    model.addAttribute("inscription",inscription);
    return "/register";
  }
}
