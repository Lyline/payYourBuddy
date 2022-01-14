package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String bankAccount;
  private boolean isValidation;
}
