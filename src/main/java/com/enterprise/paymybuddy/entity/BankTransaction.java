package com.enterprise.paymybuddy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 Defines the bank transaction object, data object. A bank's transaction is an external money transaction between the
 user and user's bank from to bank account number.

 @version 0.1

 @see com.enterprise.paymybuddy.dto.BankTransactionDTO
 @see com.enterprise.paymybuddy.dto.BankTransactionCreationDTO
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "bank_transaction")
public class BankTransaction {
  /**
   The transaction id in the database.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  /**
   The user on which the transaction processed.
   */
  @ManyToOne
  @JoinColumn(name = "user_id",referencedColumnName = "user_Id",nullable = false)
  private User user;

  /**
   The description of the transaction.
   */
  @Column(columnDefinition = "varchar (100)",
      nullable = false)
  private String description;

  /**
   The value of the transaction.
   */
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
