package com.enterprise.paymybuddy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @ManyToOne
  @JoinColumn(name = "user_id",referencedColumnName = "user_Id",nullable = false)
  private User user;

  @Column(columnDefinition = "varchar (100)",
      nullable = false)
  private String description;

  @Column(columnDefinition = "numeric (10,2)",
      nullable = false)
  private double value;

  public BankTransaction(Long id,User user, String description, double value) {
    this.transactionId=id;
    this.user =user;
    this.description = description;
    this.value = value;
  }
}
