package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class UserTransactionCreationDTO {

  @NotNull
  private Long debtorId;

  @NotNull
  private Long creditorId;

  @NotNull(message = "Description is required")
  private String description;

  @NotNull
  @Min(message = "Supported only number with point or comma", value = 0L)
  private double value;

  public UserTransactionCreationDTO(Long debtorId, Long creditorId, String description, double value) {
    this.debtorId = debtorId;
    this.creditorId = creditorId;
    this.description = description;
    this.value = value;
  }
}
