package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the user's transaction DTO.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.UserTransaction
 @see UserDTO
 @see com.enterprise.paymybuddy.entity.User
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserTransactionDTO {
  /**
   The transaction id on the database.
   */
  private Long id;

  /**
   The debtor's user, it's use a UserDTO object.
   */
  private UserDTO debtor;

  /**
   The creditor's user, it's use a UserDTO object.
   */
  private UserDTO creditor;

  /**
   The description of the transaction.
   */
  private String description;

  /**
   The value of the transaction.
   */
  private double value;
}
