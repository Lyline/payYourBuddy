package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

  private UserRepository repository=mock(UserRepository.class);
  private UserService classUnderTest=new UserServiceImpl(repository);


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
}