package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserTransactionCreationDTO {

  private Long debtorId;
  private Long creditorId;
  private String description;
  private double value;
}
