package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 The user's transaction repository interface. It's extend to JpaRepository.

 @see UserTransaction
 @see com.enterprise.paymybuddy.entity.User
 */
@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {
  /**
   Finds all user's transactions of a user that either debtor or creditor sorted by transaction id in descending order.

   @param userId    The user id
   @param sameId    The user id
   @param pageable  The pageable interface
   @return          Pages of credit and debit user's transaction of this user
   */
  Page<UserTransaction> findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(Long userId, Long sameId,
                                                                                                     Pageable pageable);

  /**
   Finds all user's transactions of a user that either debtor or creditor.

   @param userId    The user id
   @param sameId    The user id
   @return          The list of credit and debit user's transactions of this user
   */
  List<UserTransaction> findUserTransactionsByCreditor_UserIdOrDebtor_UserId(Long userId, Long sameId);
}
