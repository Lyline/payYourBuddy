package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {
  Page<UserTransaction> findUserTransactionsByCreditor_UserIdOrDebtor_UserId(Long userId, Long sameId, Pageable pageable);
}
