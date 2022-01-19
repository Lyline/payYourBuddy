package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserRepository;
import com.enterprise.paymybuddy.jpa.UserTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 The user transaction service implementation. It's extend to UserTransactionService.

 @version 0.1

 @see UserTransaction
 @see User
 @see Commission
 */
@Service
public class UserTransactionServiceImpl implements UserTransactionService{
  private final UserTransactionRepository transactionRepository;
  private final UserRepository userRepository;
  private final CommissionServiceImpl commissionService;

  public UserTransactionServiceImpl(UserTransactionRepository transactionRepository,
                                    UserRepository userRepository, CommissionServiceImpl commissionService) {
    this.transactionRepository = transactionRepository;
    this.userRepository=userRepository;
    this.commissionService = commissionService;
  }

  /**

   @param id        The user id
   @param pageable  The Pageable interface
   @return          Pages of credit and debit user's transaction of this user
   */
  @Override
  public Page<UserTransaction> getAllTransactions(Long id, Pageable pageable) {
    return transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(id,id,pageable);
  }

  /**

   @param id  The user id
   @return    The user transaction with the highest transaction id from the user list transactions if it's exist or null
   */
  @Override
  public UserTransaction getLastTransaction(Long id){
    List<UserTransaction> transactions=transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(id,id);

    return transactions.stream()
        .max(Comparator.comparing(UserTransaction::getTransactionId))
        .orElse(new UserTransaction());
  }

  /**

   @param transactionForm The transaction attributes : debtor id, creditor id, description and value of transaction
   @return                True if the transaction is validated, else false
   */
  @Override
  @Transactional
  public boolean createTransaction(UserTransactionCreationDTO transactionForm) {
    UserTransaction transaction=new UserTransaction();

    Commission commission=commissionService.calculate(transactionForm.getValue());

    //get debtor id
    User debtor= userRepository.getById(transactionForm.getDebtorId());
    double debtorBalance= debtor.getBalance();

    //get creditor id
    User creditor= userRepository.getById(transactionForm.getCreditorId());
    double creditorBalance= creditor.getBalance();

    try {
      if (debtorBalance>= transactionForm.getValue()+ commission.getValue()){

        //subtract transaction value to debtor balance
        debtor.setBalance(debtorBalance-transactionForm.getValue()- commission.getValue());

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

        commissionService.save(commission);

      }else throw new ArithmeticException();
    } catch (ArithmeticException e) {
      System.out.println("not enough money");
      return false;
    }
    return true;
  }
}
