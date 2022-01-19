package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the user registration DTO. It's use for the creation of a new user's account.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.User
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RegisterDTO {
  /**
   The first name of the new user.
   */
  private String firstName;

  /**
   The last name of the new user.
   */
  private String lastName;

  /**
   The email of the new user.
   */
  private String email;

  /**
   The password of the new user.
   */
  private String password;

  /**
   The bank account number of the new user.
   */
  private String bankAccount;

  /**
   The validation of the registration form. If the form is validated then isValidated is equal true.
   */
  private boolean isValidated;
}
