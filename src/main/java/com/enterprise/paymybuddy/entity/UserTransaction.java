package com.enterprise.paymybuddy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class UserTransaction extends Transaction{

  /**
   The creditor's user.
   */
  @ManyToOne
  private User creditor=new User();

  public UserTransaction(Long id, User debtor, User creditor,
                         String description, double value, Commission commission) {
    super(id,debtor,description,value,commission);
    this.creditor=creditor;
  }
}
