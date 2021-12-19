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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/{id}")
public class TransactionController {

  private final UserServiceImpl userService;
  private final UserTransactionServiceImpl userTransactionService;
  private final BankTransactionServiceImpl bankTransactionService;

  private ModelMapper mapper=new ModelMapper();

  public TransactionController(UserServiceImpl userService,
                               UserTransactionServiceImpl userTransactionService,
                               BankTransactionServiceImpl bankTransactionService) {
    this.userService = userService;
    this.userTransactionService=userTransactionService;
    this.bankTransactionService=bankTransactionService;
  }

  @GetMapping("/user_transactions")
  public String showUserTransactions(Model model, @PathVariable Long id,
                                     @RequestParam("page") Optional<Integer> userPage,
                                     @RequestParam("size") Optional<Integer> userSize){
    int currentPageUser= userPage.orElse(1);
    int pageSizeUser= userSize.orElse(3);

    //User
    User user= userService.getUser(id).get();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

   //Friends
    List<User> friends=user.getFriends();
    List<UserDTO> friendList=new ArrayList<>();

    for (User friendUser:friends){
      UserDTO friendDTO=mapper.map(friendUser,UserDTO.class);
      friendList.add(friendDTO);
    }

    //User transactions
    Page<UserTransaction> userTransactions=userTransactionService.getAllTransactions(id, PageRequest.of(currentPageUser-1,pageSizeUser));
    Page<UserTransactionDTO> userTransactionsDTO = userTransactions
        .map(transaction -> mapper.map(transaction,UserTransactionDTO.class));

    //Pagination
    if (userTransactionsDTO.getTotalPages() > 0) {
      List<Integer> userTransactionPageNumbers = IntStream.rangeClosed(1, userTransactionsDTO.getTotalPages())
          .boxed()
          .collect(Collectors.toList());
      model.addAttribute("userTransactionPageNumbers", userTransactionPageNumbers);
    }

    model.addAttribute("user",userDTO);
    model.addAttribute("friends",friends);
    model.addAttribute("userTransactions",userTransactionsDTO);

    return "userTransactions";
  }

  @GetMapping("/bank_transactions")
  public String showBankTransactions(Model model, @PathVariable Long id,
                                     @RequestParam("page") Optional<Integer> bankPage,
                                     @RequestParam("size") Optional<Integer> bankSize){
    int currentPageBank= bankPage.orElse(1);
    int pageSizeBank= bankSize.orElse(3);

    //User
    User user= userService.getUser(id).get();
    UserDTO userDTO=mapper.map(user,UserDTO.class);

    //Bank transaction
    Page<BankTransaction>bankTransactions=bankTransactionService
        .getAllTransactions(id,PageRequest.of(currentPageBank-1,pageSizeBank));
    Page<BankTransactionDTO> bankTransactionsDTO= bankTransactions
        .map(transaction -> mapper.map(transaction,BankTransactionDTO.class));

    //Pagination
    if (bankTransactionsDTO.getTotalPages()>0){
      List<Integer> bankTransactionPageNumbers = IntStream.rangeClosed(1,bankTransactionsDTO.getTotalPages())
          .boxed()
          .collect(Collectors.toList());
      model.addAttribute("bankTransactionPageNumbers",bankTransactionPageNumbers);
    }

    model.addAttribute("user",userDTO);
    model.addAttribute("bankTransactions",bankTransactions);

    return "bankTransactions";
  }
}
