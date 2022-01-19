package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the user's transaction DTO for the user's transaction creation.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.UserTransaction
 @see com.enterprise.paymybuddy.entity.User
 */
@Setter
@Getter
@RequiredArgsConstructor
public class UserTransactionCreationDTO {

  /**
   The user id of the debtor.
   */
  private Long debtorId;

  /**
   The user id of the creditor.
   */
  private Long creditorId;

  /**
   The description of the transaction.
   */
  private String description;

  /**
   The value of the transaction.
   */
  private double value;
}
