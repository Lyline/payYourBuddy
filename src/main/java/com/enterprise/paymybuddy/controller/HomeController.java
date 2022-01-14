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

  @GetMapping("/{id}")
  public String showActualInformation(Model model, @PathVariable Long id){

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
