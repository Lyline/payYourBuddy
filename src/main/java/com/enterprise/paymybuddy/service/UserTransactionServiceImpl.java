package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserRepository;
import com.enterprise.paymybuddy.jpa.UserTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTransactionServiceImpl implements UserTransactionService{
  private final UserTransactionRepository transactionRepository;
  private final UserRepository userRepository;

  public UserTransactionServiceImpl(UserTransactionRepository transactionRepository,
                                    UserRepository userRepository) {
    this.transactionRepository = transactionRepository;
    this.userRepository=userRepository;
  }

  @Override

  public Page<UserTransaction> getAllTransactions(Long id, Pageable pageable) {
    return transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(id,id,pageable);
  }

  @Override
  @Transactional
  public boolean createTransaction(UserTransactionCreationDTO transactionForm) {
    UserTransaction transaction=new UserTransaction();

    //get debtor id
    User debtor= userRepository.getById(transactionForm.getDebtorId());
    double debtorBalance= debtor.getBalance();

    //get creditor id
    User creditor= userRepository.getById(transactionForm.getCreditorId());
    double creditorBalance= creditor.getBalance();

    try {
      if (debtorBalance>= transactionForm.getValue()){

        //subtract transaction value to debtor balance
        debtor.setBalance(debtorBalance-transactionForm.getValue());

        //add transaction value to creditor balance
        creditor.setBalance(creditorBalance+transactionForm.getValue());

        //save debtor user
        userRepository.save(debtor);

        //save creditor user
        userRepository.save(creditor);

        //save transaction
        transaction.setDebtor(debtor);
        transaction.setCreditor(creditor);
        transaction.setDescription(transactionForm.getDescription());
        transaction.setValue(transactionForm.getValue());

        transactionRepository.save(transaction);

      }else throw new ArithmeticException();
    } catch (ArithmeticException e) {
      System.out.println("not enough money");
      return false;
    }
    return true;
  }
}
