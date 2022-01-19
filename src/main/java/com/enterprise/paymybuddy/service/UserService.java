package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.FriendConnexionDTO;
import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.entity.User;

import java.util.Optional;

/**
 The user service interface.

 @version 0.1

 @see User
 @see FriendConnexionDTO
 @see RegisterDTO
 */
public interface UserService {
  /**
   Gets user from to user id.

   @param id  The user id
   @return    The user if it's exist, else return exception
   */
  Optional<User>getUser(Long id);

  /**
   Creates friend link with the user.

   @param user            The user id
   @param friendConnexion The friend connexion attributes : email user and validation statement
   @return                True if the link is validated, else false
   */
  boolean createFriend(User user, FriendConnexionDTO friendConnexion);

  /**
   Creates a new user with a balance equals to 0.

   @param inscription The registration attributes : first name, last name, email, password, bank account number, validation statement
   @return            True if the creation is validated
   */
  boolean createUser(RegisterDTO inscription);
}
