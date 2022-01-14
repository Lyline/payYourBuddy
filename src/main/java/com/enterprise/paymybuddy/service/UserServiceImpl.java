package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.FriendConnexion;
import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.enterprise.paymybuddy.service.HashService.getHash;

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

  @Override
  @Transactional
  public boolean createFriend(User user, FriendConnexion friendConnexion) {
    String emailEncoded=getHash(friendConnexion.getEmail());

    User friend = repository.getUserByEmail(emailEncoded);

    if (friend != null) {
      user.getFriends().add(friend);
      repository.save(user);
      return true;
    } else return false;
  }

  @Override
  @Transactional
  public boolean createUser(RegisterDTO inscription) {
    User response=repository.getUserByEmail(inscription.getEmail());

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
