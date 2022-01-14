package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.BankTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
  Page<BankTransaction> findBankTransactionsByUserUserId(Long id, Pageable pageable);
  List<BankTransaction> findBankTransactionsByUserUserId(Long id);
}
