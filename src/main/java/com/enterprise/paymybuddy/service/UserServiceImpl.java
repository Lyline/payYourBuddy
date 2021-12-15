package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<User> getUser(Long id){
    return Optional.ofNullable(repository.findById(id)
        .orElseThrow(()->new NoSuchElementException("User id "+id+" not found")));
  }
}
