package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserTransactionServiceImpl implements UserTransactionService{
  private final UserTransactionRepository repository;

  public UserTransactionServiceImpl(UserTransactionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Page<UserTransaction> getAllTransactions(Long id, Pageable pageable) {
    return repository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(id,id,pageable);
  }
}
