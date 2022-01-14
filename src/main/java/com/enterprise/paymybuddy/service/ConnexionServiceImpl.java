package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.enterprise.paymybuddy.service.HashService.getHash;

@Service
public class ConnexionServiceImpl implements ConnexionService {
  private final UserRepository repository;

  public ConnexionServiceImpl(UserRepository repository) {
    this.repository=repository;
  }

  @Override
  public User connect(UserConnexionDTO logging) {
    String emailEncoded= getHash(logging.getEmail());
    String passwordEncoded= getHash(logging.getPassword());

    Optional<User> user=repository.getUserByEmailAndPassword(emailEncoded,passwordEncoded);

    return user.orElse(null);
  }
}
