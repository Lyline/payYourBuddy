package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BankTransactionCreationDTO {
  private Long userId;
  private String type;
  private String description;
  private double value;
}
