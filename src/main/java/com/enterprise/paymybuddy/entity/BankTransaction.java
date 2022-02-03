package com.enterprise.paymybuddy.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class BankTransaction extends Transaction{

  public BankTransaction(Long id,User user, String description, double value, Commission commission) {
    super(id,user,description,value,commission);
  }
}
