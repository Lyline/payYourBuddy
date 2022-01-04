package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.BankTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.BankTransactionRepository;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankTransactionServiceImpl implements BankTransactionService{
  private final BankTransactionRepository transactionRepository;
  private final UserRepository userRepository;

  public BankTransactionServiceImpl(BankTransactionRepository transactionRepository,
                                    UserRepository userRepository) {
    this.transactionRepository = transactionRepository;
    this.userRepository=userRepository;
  }

  @Override
  public Page<BankTransaction> getAllTransactions(Long id, Pageable pageable) {
    return transactionRepository.findBankTransactionsByUserUserId(id,pageable);
  }

  @Override
  @Transactional
  public boolean createTransaction(BankTransactionCreationDTO transaction) {
    BankTransaction bankTransaction=new BankTransaction();

    User user= userRepository.getById(transaction.getUserId());
    double balance=user.getBalance();

    if (transaction.getType().equals("send")){
      if(balance >= transaction.getValue()){

        //subtract value transaction to balance
        user.setBalance(balance-transaction.getValue());

        //Construct bank transaction
        bankTransaction.setUser(user);
        bankTransaction.setDescription(transaction.getDescription());
        bankTransaction.setValue(-transaction.getValue());

        //Save data
        userRepository.save(user);
        transactionRepository.save(bankTransaction);

        return true;
      }
    }

    if (transaction.getType().equals("receive")){
      //add value transaction to balance
      user.setBalance(balance+transaction.getValue());

      //Construct bank transaction
      bankTransaction.setUser(user);
      bankTransaction.setDescription(transaction.getDescription());
      bankTransaction.setValue(transaction.getValue());

      //Save data
      userRepository.save(user);
      transactionRepository.save(bankTransaction);

      return true;
    }
    return false;
  }
}
