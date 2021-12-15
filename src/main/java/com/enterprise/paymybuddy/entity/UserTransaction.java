package com.enterprise.paymybuddy.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "user_transaction")
public class UserTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long transactionId;

  @ManyToOne
  private User debtor=new User();

  @ManyToOne
  private User creditor=new User();

  @Column(columnDefinition = "varchar (150)",
      nullable = false)
  private String description;

  @Column(columnDefinition = "numeric (10,2) default 0")
  private double value;

  public UserTransaction(Long id, User debtor, User creditor,
                         String description, double value) {
    this.transactionId = id;
    this.debtor=debtor;
    this.creditor=creditor;
    this.description = description;
    this.value = value;
  }
}
