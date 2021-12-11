package com.enterprise.paymybuddy.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserTransaction {

  private Long id;
  private User debtor=new User();
  private User creditor=new User();
  private String description;
  private double value;

  public UserTransaction(Long id, User debtor, User creditor,
                         String description, double value) {
    this.id = id;
    this.debtor=debtor;
    this.creditor=creditor;
    this.description = description;
    this.value = value;
  }
}
