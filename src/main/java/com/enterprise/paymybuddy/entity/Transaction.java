package com.enterprise.paymybuddy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 Defines abstract transaction entity.

 @version 0.1

 @see BankTransaction
 @see UserTransaction
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract public class Transaction {
  /**
   The transaction id in the database.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  /**
   The debtor's user.
   */
  @ManyToOne
  @NotNull
  private User debtor=new User();

  /**
   The description of the transaction.
   */
  @NotBlank
  @Column(columnDefinition = "varchar (150)",
      nullable = false)
  private String description;

  /**
   The value of the transaction.
   */
  @NotNull
  @Column(columnDefinition = "numeric (10,2) default 0")
  private double value;

  /**
   The id of commission
   */
  @NotNull
  @OneToOne
  private Commission commission;
}
