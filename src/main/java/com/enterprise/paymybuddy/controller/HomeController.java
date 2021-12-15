package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.BankTransactionDTO;
import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.dto.UserTransactionDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;

@Controller
@RequestMapping("/")
public class HomeController {

  private final UserServiceImpl userServiceImpl;

  public HomeController(UserServiceImpl userServiceImpl) {
    this.userServiceImpl = userServiceImpl;
  }

  @GetMapping("/{id}")
  public String showActualInformation(Model model, @PathVariable Long id){

    User user= userServiceImpl.getUser(id).get();

    UserTransaction lastUserTransaction= user.getUserTransactions().stream()
          .max(Comparator.comparing(UserTransaction::getTransactionId))
          .orElse(new UserTransaction());

    BankTransaction lastBankTransaction= user.getBankTransactions().stream()
          .max(Comparator.comparing(BankTransaction::getTransactionId))
          .orElse(new BankTransaction());

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
