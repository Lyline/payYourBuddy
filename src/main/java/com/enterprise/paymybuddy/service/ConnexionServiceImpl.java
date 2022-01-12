package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnexionServiceImpl implements ConnexionService {
  private final UserRepository repository;

  public ConnexionServiceImpl(UserRepository repository) {
    this.repository=repository;
  }

  @Override
  public User connect(UserConnexionDTO logging) {
    Optional<User> user=repository.getUserByEmailAndPassword(logging.getEmail(),logging.getPassword());

    return user.orElse(null);
  }
}
