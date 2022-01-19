package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 The user transaction service interface.

 @version 0.1

 @see UserTransaction
 @see UserTransactionCreationDTO
 */
public interface UserTransactionService {

  /**
   Gets all user's transactions of a user that either debtor or creditor sorted by transaction id in descending order.

   @param id        The user id
   @param pageable  The Pageable interface
   @return          Pages of credit and debit user's transaction of this user
   */
  Page<UserTransaction> getAllTransactions(Long id, Pageable pageable);

  /**
   Gets the last user transaction of the user, debtor or creditor.

   @param id  The user id
   @return    The user transaction with the highest transaction id from the user list transactions if it's exist or null
   */
  UserTransaction getLastTransaction(Long id);

  /**
   Creates a new user transaction for the user.

   @param transaction The transaction attributes : debtor id, creditor id, description and value of transaction
   @return            True if the transaction is validated, else false
   */
  boolean createTransaction(UserTransactionCreationDTO transaction);
}
