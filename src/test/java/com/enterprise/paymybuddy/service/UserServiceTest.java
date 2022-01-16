package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.FriendConnexion;
import com.enterprise.paymybuddy.dto.RegisterDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.enterprise.paymybuddy.service.HashService.getHash;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

  private final UserRepository repository=mock(UserRepository.class);
  private final UserService classUnderTest=new UserServiceImpl(repository);


  @Test
  void givenAnExistingUserWhenGetUserThenReturnThisUser() {
    //Given
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    when(repository.findById(1L)).thenReturn(java.util.Optional.of(user));

    //When
    Optional<User> actual=classUnderTest.getUser(1L);

    //Then
    assertSame(user, actual.get());
    verify(repository,times(1)).findById(1L);
  }

  @Test
  void givenANotExistingUserWhenGetUserThenReturnException() {
    //Given
    User user=null;
    when(repository.findById(1L)).thenReturn(Optional.ofNullable(user));

    //When
    Throwable thrown=catchThrowable(()->classUnderTest.getUser(1L));

    //Then
    assertThat(thrown)
        .isInstanceOf(NoSuchElementException.class)
        .hasMessageContaining("User id 1 not found");
  }

  @Test
  void givenAUserAndAFriendWhenCreateFriendThenUserLinkToFriend() {
    //Given
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    User friend=new User("Steve","Roger","steve@test.com","pw","bank",100.);

    FriendConnexion friendConnexion=new FriendConnexion();
    friendConnexion.setEmail("steve@test.com");

    when(repository.getUserByEmail(anyString())).thenReturn(friend);

    //When
    boolean actual= classUnderTest.createFriend(user,friendConnexion);

    //Then
    assertTrue(actual);
    assertThat(user.getFriends().size()).isEqualTo(1);
    verify(repository,times(1)).getUserByEmail(getHash(friendConnexion.getEmail()));
    verify(repository,times(1)).save(user);
  }

  @Test
  void givenAUserWithoutFriendWhenCreateFriendThenUserHaveNotFriend() {
    //Given
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);

    FriendConnexion friendConnexion=new FriendConnexion();
    friendConnexion.setEmail("steve@test.com");

    when(repository.getUserByEmail(anyString())).thenReturn(null);

    //When
    boolean actual= classUnderTest.createFriend(user,friendConnexion);

    //Then
    assertFalse(actual);
    assertThat(user.getFriends().size()).isEqualTo(0);
    verify(repository,times(1)).getUserByEmail(getHash(friendConnexion.getEmail()));
    verify(repository,times(0)).save(user);
  }

  @Test
  void givenAUserAndAAlreadyExistFriendWhenCreateFriendThenFriendLinkNotDuplicated() {
    //Given
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    User friend=new User("Steve","Roger","steve@test.com","pw","bank",100.);

    user.getFriends().add(friend);

    FriendConnexion friendConnexion=new FriendConnexion();
    friendConnexion.setEmail("steve@test.com");

    when(repository.getUserByEmail(anyString())).thenReturn(friend);

    //When
    boolean actual= classUnderTest.createFriend(user,friendConnexion);

    //Then
    assertFalse(actual);
    assertThat(user.getFriends().size()).isEqualTo(1);
    verify(repository,times(1)).getUserByEmail(getHash(friendConnexion.getEmail()));
    verify(repository,times(0)).save(user);
  }

  @Test
  void givenAPersonWhenCreateAccountWithValidInformationsThenReturnTrue() {
    //Given
    User user=new User("Tony","Stark",
        "73062d872926c2a556f17b36f50e328ddf9bff9d403939bd14b6c3b7f5a33fc2",
        "30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4",
        "bank",0);

    RegisterDTO inscription=new RegisterDTO();
    inscription.setFirstName("Tony");
    inscription.setLastName("Stark");
    inscription.setEmail("test@email.com");
    inscription.setPassword("pw");
    inscription.setBankAccount("bank");

   when(repository.getUserByEmail(anyString())).thenReturn(null);

    //When
    boolean actual= classUnderTest.createUser(inscription);

    //Then
    assertTrue(actual);
    verify(repository,times(1)).getUserByEmail(inscription.getEmail());
    verify(repository,times(1)).save(user);
  }

  @Test
  void givenAPersonWhenCreateAccountWithExistEmailThenReturnFalse() {
    //Given
    User user=new User("Tony","Stark","test@email.com",
        "pw","bank",0);
    User userExist=new User("John","Doe","test@email.com",
        "pw","bank",0);

    RegisterDTO inscription=new RegisterDTO();
    inscription.setFirstName("Tony");
    inscription.setLastName("Stark");
    inscription.setEmail("test@email.com");
    inscription.setPassword("pw");
    inscription.setBankAccount("bank");

    when(repository.getUserByEmail(anyString())).thenReturn(userExist);

    //When
    boolean actual= classUnderTest.createUser(inscription);

    //Then
    assertFalse(actual);
    verify(repository,times(1)).getUserByEmail(inscription.getEmail());
    verify(repository,times(0)).save(user);
  }
}