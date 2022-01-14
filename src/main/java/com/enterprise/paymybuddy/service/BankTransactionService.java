package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.BankTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankTransactionService {
  Page<BankTransaction> getAllTransactions(Long id, Pageable pageable);
  BankTransaction getLastTransaction(Long id);
  boolean createTransaction(BankTransactionCreationDTO transaction);
}
