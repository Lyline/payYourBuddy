package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.enterprise.paymybuddy.service.HashService.getHash;

/**
 The connexion service implementation. It's extend of ConnexionService.

 @version 0.1

 @see User
 @see UserConnexionDTO
 */
@Service
public class ConnexionServiceImpl implements ConnexionService {
  private final UserRepository repository;

  public ConnexionServiceImpl(UserRepository repository) {
    this.repository=repository;
  }

  /**

   @param logging The connexion attributes : email and password of the user
   @return        The user if it's exist, else null
   */
  @Override
  public User connect(UserConnexionDTO logging) {
    String emailEncoded= getHash(logging.getEmail());
    String passwordEncoded= getHash(logging.getPassword());

    Optional<User> user=repository.getUserByEmailAndPassword(emailEncoded,passwordEncoded);

    return user.orElse(null);
  }
}
