package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.dto.*;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 The transaction controller allow display bank and user transaction forms and create new transactions.

 @version 0.1

 @see User
 @see UserDTO
 @see UserTransaction
 @see UserTransactionDTO
 @see UserTransactionCreationDTO
 @see BankTransaction
 @see BankTransactionDTO
 @see BankTransactionCreationDTO
 */
@Controller
@RequestMapping("/{id}")
public class TransactionController{

  private final UserServiceImpl userService;
  private final UserTransactionServiceImpl userTransactionService;
  private final BankTransactionServiceImpl bankTransactionService;

  private final ModelMapper mapper=new ModelMapper();

  public TransactionController(UserServiceImpl userService,
                               UserTransactionServiceImpl userTransactionService,
                               BankTransactionServiceImpl bankTransactionService) {
    this.userService = userService;
    this.userTransactionService=userTransactionService;
    this.bankTransactionService=bankTransactionService;
  }

  /**
   Display the user transaction webpage with the user transaction form.

   @param id        The user id
   @param userPage  The current page of user transactions displayed
   @param userSize  The number of user transaction per page displayed
   @param model     The model interface
   @return          The user transaction webpage
   */
  @GetMapping("/user_transactions")
  public String showUserTransactions(@PathVariable Long id,
                                     @RequestParam("page") Optional<Integer> userPage,
                                     @RequestParam("size") Optional<Integer> userSize,Model model){
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
    Page<UserTransaction> userTransactions=userTransactionService.getAllTransactions(id, PageRequest.of(currentPageUser-1,
        pageSizeUser));
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
    model.addAttribute("newTransaction",new UserTransactionCreationDTO());

    return "userTransactions";
  }

  /**
   Post the user transaction form, if it's validated return the validation transaction webpage, else return error
   transaction webpage.

   @param id          The user id
   @param transaction The transaction attributes : debtor id, creditor id, description of transaction and the value
   @return            The validation transaction webpage, else return error transaction webpage
   */
  @PostMapping("/user_transactions")
  public String processUserTransaction(@Valid @ModelAttribute("newTransaction") UserTransactionCreationDTO transaction,
                                       @PathVariable Long id){
    transaction.setDebtorId(id);

    boolean transactionValidated=userTransactionService.createTransaction(transaction);

    if (transactionValidated) {
      return "transactionSuccess";
    } else {
      return "transactionAborted";
    }
  }

  /**
   Display the bank transaction webpage with the bank transaction form.

   @param id        The user id
   @param userPage  The current page of user transactions displayed
   @param userSize  The number of user transaction per page displayed
   @param model     The model interface
   @return          The bank transaction webpage
   */
  @GetMapping("/bank_transactions")
  public String showBankTransactions(@PathVariable Long id,
                                     @RequestParam("page") Optional<Integer> userPage,
                                     @RequestParam("size") Optional<Integer> userSize, Model model){
    int currentPageBank= userPage.orElse(1);
    int pageSizeBank= userSize.orElse(3);

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
    model.addAttribute("newTransaction",new BankTransactionCreationDTO());

    return "bankTransactions";
  }

  /**
   Post the bank transaction form, if it's validated return the validation transaction webpage, else return error
   transaction webpage.

   @param id          The user id
   @param transaction The transaction attributes : user id, type of transaction <i>send</i> or <i>receive</i>, description
   and the value
   @return            The validation transaction webpage, else return error transaction webpage
   */
  @PostMapping("/bank_transactions")
  public String processBankTransaction(@Valid @ModelAttribute("newTransaction")BankTransactionCreationDTO transaction,
                                       @PathVariable Long id){
    transaction.setUserId(id);

    boolean transactionValidated=bankTransactionService.createTransaction(transaction);

    if (transactionValidated) {
      return "transactionSuccess";
    } else {
      return "transactionAborted";
    }
  }
}
