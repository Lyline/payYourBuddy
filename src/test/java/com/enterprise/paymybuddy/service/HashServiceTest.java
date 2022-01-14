package com.enterprise.paymybuddy.service;

import org.junit.jupiter.api.Test;

import static com.enterprise.paymybuddy.service.HashService.getHash;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HashServiceTest {

  @Test
  void givenAStringValueWhenGetHashThenReturnHashStringValue() {
    //Given
    String message="hello world";
    String response="b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9";

    //When
    String actual= getHash(message);

    //Then
    assertThat(actual).isEqualTo(response);
  }
}