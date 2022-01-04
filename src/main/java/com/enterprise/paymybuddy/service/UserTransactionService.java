package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserTransactionService {
  Page<UserTransaction> getAllTransactions(Long id, Pageable pageable);
  boolean createTransaction(UserTransactionCreationDTO transaction);
}
