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

  public BankTransactionCreationDTO(Long userId, String type, String description, double value) {
    this.userId = userId;
    this.type = type;
    this.description = description;
    this.value = value;
  }
}
