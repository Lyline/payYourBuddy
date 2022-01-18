package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserTransactionDTO {
  private Long id;
  private UserDTO debtor;
  private UserDTO creditor;
  private String description;
  private double value;
}
