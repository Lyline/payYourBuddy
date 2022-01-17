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

  public UserTransactionCreationDTO(Long debtorId, Long creditorId, String description, double value) {
    this.debtorId = debtorId;
    this.creditorId = creditorId;
    this.description = description;
    this.value = value;
  }
}
