package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnexionServiceImplTest {
  private final UserRepository repository=mock(UserRepository.class);
  private final ConnexionService classUnderTest= new ConnexionServiceImpl(repository);

  @Test
  void givenAExistUserWhenConnectThenReturnUserId() {
    //Given
    UserConnexionDTO logging=new UserConnexionDTO();
    logging.setEmail("tony@test.com");
    logging.setPassword("pw");

    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    user.setUserId(1L);

    when(repository.getUserByEmailAndPassword(anyString(),anyString())).thenReturn(java.util.Optional.of(user));

    //When
    User actual=classUnderTest.connect(logging);

    //Then
    assertThat(actual.getUserId()).isEqualTo(1L);
  }

  @Test
  void givenANotExistUserWhenConnectThenReturnZero() {
    UserConnexionDTO logging=new UserConnexionDTO();
    logging.setEmail("tony@test.com");
    logging.setPassword("pw");

    User user=null;

    when(repository.getUserByEmailAndPassword(anyString(),anyString())).thenReturn(java.util.Optional.ofNullable(user));

    //When
    User actual=classUnderTest.connect(logging);

    //Then
    assertNull(actual);
  }
}
