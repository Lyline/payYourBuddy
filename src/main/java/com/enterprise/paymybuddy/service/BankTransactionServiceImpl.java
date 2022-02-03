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

/**
 The bank's transaction service implementation. It's implement BankTransactionService

 @version 0.1

 @see BankTransactionRepository
 @see UserRepository
 @see CommissionService
 */
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

  /**

   @param id        The user id
   @param pageable  The pageable interface
   @return          Pages of bank's transaction of this user sorted by transaction id in descending order
   */
  @Override
  public Page<BankTransaction> getAllTransactions(Long id, Pageable pageable) {
    return transactionRepository.findBankTransactionsByDebtor_UserIdOrderByTransactionIdDesc(id,pageable);
  }

  /**

   @param id  The user id
   @return    The bank transaction with the highest transaction id from the user list transactions if it's exist or null
   */
  @Override
  public BankTransaction getLastTransaction(Long id){
    List<BankTransaction> transactions=transactionRepository.findBankTransactionsByDebtor_UserId(id);

    return transactions.stream()
        .max(Comparator.comparing(BankTransaction::getTransactionId))
        .orElse(new BankTransaction());
  }

  /**

   @param transaction The transaction attributes : user id, transaction type, description and value of transaction
   @return            True if the transaction is validated, else false
   */
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
        bankTransaction.setDebtor(user);
        bankTransaction.setDescription(transaction.getDescription());
        bankTransaction.setValue(-transaction.getValue());
        bankTransaction.setCommission(commission);

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
      bankTransaction.setDebtor(user);
      bankTransaction.setDescription(transaction.getDescription());
      bankTransaction.setValue(transaction.getValue());
      bankTransaction.setCommission(commission);

      //Save data
      userRepository.save(user);

      commissionService.save(commission);
      transactionRepository.save(bankTransaction);

      return true;
    }
    return false;
  }
}
