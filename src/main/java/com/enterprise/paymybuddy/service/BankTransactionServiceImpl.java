package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.BankTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.BankTransactionRepository;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class BankTransactionServiceImpl implements BankTransactionService{
  private final BankTransactionRepository transactionRepository;
  private final UserRepository userRepository;
  private final CommissionServiceImpl commissionService;

  public BankTransactionServiceImpl(BankTransactionRepository transactionRepository,
                                    UserRepository userRepository,
                                    CommissionServiceImpl commissionService) {
    this.transactionRepository = transactionRepository;
    this.userRepository=userRepository;
    this.commissionService=commissionService;
  }

  @Override
  public Page<BankTransaction> getAllTransactions(Long id, Pageable pageable) {
    return transactionRepository.findBankTransactionsByUserUserId(id,pageable);
  }

  public BankTransaction getLastTransaction(Long id){
    List<BankTransaction> transactions=transactionRepository.findBankTransactionsByUserUserId(id);

    return transactions.stream()
        .max(Comparator.comparing(BankTransaction::getTransactionId))
        .orElse(new BankTransaction());
  }

  @Override
  @Transactional
  public boolean createTransaction(BankTransactionCreationDTO transaction) {
    BankTransaction bankTransaction=new BankTransaction();
    Commission commission=commissionService.calculate(transaction.getValue());

    User user= userRepository.getById(transaction.getUserId());
    double balance=user.getBalance();

    if (transaction.getType().equals("send")){
      if(balance >= transaction.getValue()+ commission.getValue()){

        //subtract value transaction to balance
        user.setBalance(balance-transaction.getValue()- commission.getValue());

        //Construct bank transaction
        bankTransaction.setUser(user);
        bankTransaction.setDescription(transaction.getDescription());
        bankTransaction.setValue(-transaction.getValue());

        //Save data
        userRepository.save(user);
        transactionRepository.save(bankTransaction);
        commissionService.save(commission);

        return true;
      }
    }

    if (transaction.getType().equals("receive")){
      //add value transaction to balance
      user.setBalance(balance+transaction.getValue()- commission.getValue());

      //Construct bank transaction
      bankTransaction.setUser(user);
      bankTransaction.setDescription(transaction.getDescription());
      bankTransaction.setValue(transaction.getValue());

      //Save data
      userRepository.save(user);
      transactionRepository.save(bankTransaction);
      commissionService.save(commission);

      return true;
    }
    return false;
  }
}
