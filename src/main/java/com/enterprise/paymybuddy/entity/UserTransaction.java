package com.enterprise.paymybuddy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 Defines the user's transaction object, data object. A user's transaction is an internal money transaction between two
 users.

 @version 0.1

 @see User
 @see com.enterprise.paymybuddy.dto.UserTransactionDTO
 @see com.enterprise.paymybuddy.dto.UserTransactionCreationDTO
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "user_transaction")
public class UserTransaction {

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
  private User debtor=new User();

  /**
   The creditor's user.
   */
  @ManyToOne
  private User creditor=new User();

  /**
   The description of the transaction.
   */
  @Column(columnDefinition = "varchar (150)",
      nullable = false)
  private String description;

  /**
   The value of the transaction.
   */
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
