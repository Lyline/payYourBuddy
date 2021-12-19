package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.jpa.BankTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankTransactionServiceImpl implements BankTransactionService{
  private final BankTransactionRepository repository;

  public BankTransactionServiceImpl(BankTransactionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Page<BankTransaction> getAllTransactions(Long id, Pageable pageable) {
    return repository.findBankTransactionsByUserUserId(id,pageable);
  }
}
