package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the bank transaction DTO for the bank transaction creation.

 @version 0.1

 @see com.enterprise.paymybuddy.entity.BankTransaction
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BankTransactionCreationDTO {
  /**
   The user id of this transaction.
   */
  private Long userId;

  /**
   The type of transaction : <i>send</i> or <i>receive</i>.
   */
  private String type;

  /**
   The description of transaction.
   */
  private String description;

  /**
   The value of transaction.
   */
  private double value;
}
