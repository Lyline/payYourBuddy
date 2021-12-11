package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.BankTransactionDTO;
import com.enterprise.paymybuddy.dto.UserDTO;
import com.enterprise.paymybuddy.dto.UserTransactionDTO;
import com.enterprise.paymybuddy.model.BankTransaction;
import com.enterprise.paymybuddy.model.User;
import com.enterprise.paymybuddy.model.UserTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;

@Controller
@RequestMapping("/")
public class HomeController {


  @GetMapping
  public String showActualInformation(Model model){
    //start create data
    //create 3 users
    User user=new User(1L,"Tony","Stark","tony@mail.com",
        "test","bankAccount1",100.);
    User user1=new User(2L,"Steve","Rogers","steve@mail.com",
        "test","bankAccount2",100.);
    User user2=new User(3L,"Natacha","Romanov","natacha@mail.com",
        "test","bankAccount3",100.);

    //create 3 userTransaction
    UserTransaction userTransaction=new UserTransaction(1L, user, user1,
        "new transaction1",10.);
    UserTransaction userTransaction1=new UserTransaction(2L, user2, user,
        "new transaction2",20.);
    UserTransaction userTransaction2=new UserTransaction(3L, user, user1,
        "new transaction3",30.);

    //create 3 bankTransaction
    BankTransaction bankTransaction=new BankTransaction(1L,1L,"bankAccount1",
        "bank transaction1",10.);
    BankTransaction bankTransaction1=new BankTransaction(2L,1L,"bankAccount1",
        "bank transaction1",20.);
    BankTransaction bankTransaction2=new BankTransaction(3L,1L,"bankAccount1",
        "bank transaction1",-30.);

    user.getFriends().add(user1);
    user.getFriends().add(user2);

    user.getUserTransactions().add(userTransaction);
    user.getUserTransactions().add(userTransaction1);
    user.getUserTransactions().add(userTransaction2);

    user.getBankTransactions().add(bankTransaction);
    user.getBankTransactions().add(bankTransaction1);
    user.getBankTransactions().add(bankTransaction2);
  // End create Data

    UserTransaction lastUserTransaction= user.getUserTransactions().stream()
          .max(Comparator.comparing(UserTransaction::getId))
          .orElse(new UserTransaction());

    BankTransaction lastBankTransaction= user.getBankTransactions().stream()
          .max(Comparator.comparing(BankTransaction::getId))
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
