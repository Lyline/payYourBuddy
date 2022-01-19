package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the user connexion DTO. It's use for the connexion by the user.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.User
 */
@Setter
@Getter
@RequiredArgsConstructor
public class UserConnexionDTO {

  /**
   The email of the user.
   */
  private String email;

  /**
   The password of the user.
   */
  private String password;

  /**
   The validation connexion's form. If the email is linked to the password then isConnected is equal to true.
   */
  private boolean isConnected;
}
