package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
}
