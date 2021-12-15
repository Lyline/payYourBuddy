package com.enterprise.paymybuddy.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
