package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the user DTO, it's use all the application for the user's session

 @version 0.1

 @see com.enterprise.paymybuddy.entity.User
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
  /**
   The user id in the database.
   */
  private Long id;

  /**
   The first name of the user.
   */
  private String firstName;

  /**
   The last name of the user.
   */
  private String lastName;

  /**
   The balance of the user's account in the application.
   */
  private String balance;

}
