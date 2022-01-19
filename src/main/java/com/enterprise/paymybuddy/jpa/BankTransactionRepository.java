package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.BankTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 The bank's transaction repository interface. It's extend to JpaRepository.

 @see BankTransaction
 @see com.enterprise.paymybuddy.entity.User
 */
@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
  /**
   Find all bank's transactions of a user sorted by transaction id in descending order.

   @param id        The user id
   @param pageable  The pageable interface
   @return          Pages of bank's transaction of this user sorted by transaction id in descending order
   */
  Page<BankTransaction> findBankTransactionsByUserUserIdOrderByTransactionIdDesc(Long id, Pageable pageable);

  /**
   Find all bank's transactions of a user.

   @param id  The user id
   @return    The list of bank transactions of this user
   */
  List<BankTransaction> findBankTransactionsByUserUserId(Long id);
}
