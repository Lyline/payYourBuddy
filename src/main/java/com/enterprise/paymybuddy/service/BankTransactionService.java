package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.BankTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 The bank transaction service interface.

 @version 0.1

 @see BankTransaction
 @see com.enterprise.paymybuddy.entity.User
 */
public interface BankTransactionService {
  /**
   Gets all bank transactions of the user.

   @param id        The user id
   @param pageable  The pageable interface
   @return          Pages of bank's transaction of this user sorted by transaction id in descending order
   */
  Page<BankTransaction> getAllTransactions(Long id, Pageable pageable);

  /**
   Gets the last bank transaction of the user.

   @param id  The user id
   @return    The bank transaction with the highest transaction id from the user list transactions if it's exist or null
   */
  BankTransaction getLastTransaction(Long id);

  /**
   Creates a new bank transaction for the user

   @param transaction The transaction attributes : user id, transaction type, description and value of transaction
   @return            True if the transaction is validated, else false
   */
  boolean createTransaction(BankTransactionCreationDTO transaction);
}
