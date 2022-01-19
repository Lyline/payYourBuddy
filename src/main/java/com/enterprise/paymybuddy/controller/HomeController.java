package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.BankTransactionDTO;
import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.dto.UserTransactionDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.service.BankTransactionServiceImpl;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import com.enterprise.paymybuddy.service.UserTransactionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 The home controller allow display the homepage of a user.

 @version 0.1

 @see User
 @see UserTransaction
 @see UserTransactionDTO
 @see BankTransaction
 @see BankTransactionDTO
 */
@Controller
@RequestMapping("/")
public class HomeController {

  private final UserServiceImpl userService;
  private final UserTransactionServiceImpl userTransactionService;
  private final BankTransactionServiceImpl bankTransactionService;

  public HomeController(UserServiceImpl userService, UserTransactionServiceImpl userTransactionService, BankTransactionServiceImpl bankTransactionService) {
    this.userService = userService;
    this.userTransactionService = userTransactionService;
    this.bankTransactionService = bankTransactionService;
  }

  /**
   Displays the homepage of the user, if it's connected. It's display the current balance, the last user transaction and
   the last bank transaction

   @param id    The user id
   @param model The model interface
   @return      The homepage of the user
   */
  @GetMapping("/{id}")
  public String showActualInformation(@PathVariable Long id, Model model ){

    User user= userService.getUser(id).get();

    UserTransaction lastUserTransaction= userTransactionService.getLastTransaction(id);
    BankTransaction lastBankTransaction= bankTransactionService.getLastTransaction(id);

    ModelMapper mapper=new ModelMapper();
    UserDTO userDTO=mapper.map(user, UserDTO.class);
    UserTransactionDTO userTransactionDTO= mapper.map(lastUserTransaction,UserTransactionDTO.class);
    BankTransactionDTO bankTransactionDTO=mapper.map(lastBankTransaction,BankTransactionDTO.class);

    model.addAttribute("user",userDTO);
    model.addAttribute("lastUserTransaction",userTransactionDTO);
    model.addAttribute("lastBankTransaction",bankTransactionDTO);

    return"home";
  }
}
