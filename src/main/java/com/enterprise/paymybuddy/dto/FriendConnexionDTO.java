package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the friend's connexion DTO. It's use for the friend's research.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.User
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FriendConnexionDTO {
  /**
   The user's email of potentiel friend.
   */
  private String email;

  /**
   The validation of user's existence researched. If the user's email exist isValidated is equal to true.
   */
  private boolean isValidated;
}
