package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.FriendConnexionDTO;
import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.enterprise.paymybuddy.service.HashService.getHash;

/**
 The user service implementation. It's extend to UserService.

 @version 0.1

 @see User
 @see FriendConnexionDTO
 @see RegisterDTO
 */
@Service
public class UserServiceImpl implements UserService{

  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  /**

   @param id  The user id
   @return    The user if it's exist, else return NoSuchElementException
   */
  @Override
  public Optional<User> getUser(Long id){
    return Optional.ofNullable(repository.findById(id)
        .orElseThrow(()->new NoSuchElementException("User id "+id+" not found")));
  }

  /**

   @param user            The user id
   @param friendConnexion The friend connexion attributes : email user and validation statement
   @return                True if the link is validated, else false
   */
  @Override
  @Transactional
  public boolean createFriend(User user, FriendConnexionDTO friendConnexion) {
    String emailEncoded=getHash(friendConnexion.getEmail());

    User friend = repository.getUserByEmail(emailEncoded);

    if (friend != null) {
      if (!user.getFriends().contains(friend)){
        user.getFriends().add(friend);
        repository.save(user);
        return true;
      }else return false;
    } else return false;
  }

  /**

   @param inscription The registration attributes : first name, last name, email, password, bank account number, validation statement
   @return            True if the creation is validated
   */
  @Override
  @Transactional
  public boolean createUser(RegisterDTO inscription) {
    User response=repository.getUserByEmail(getHash(inscription.getEmail()));

    if (response==null){
      String emailEncoded= getHash(inscription.getEmail());
      String passwordEncoded= getHash(inscription.getPassword());

      User user= new User(inscription.getFirstName(), inscription.getLastName(),
          emailEncoded, passwordEncoded,inscription.getBankAccount(),0);

      repository.save(user);
      return true;
    }
    return false;
  }
}
