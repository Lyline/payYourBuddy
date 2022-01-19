package com.enterprise.paymybuddy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 Defines the commission object, data object. It's a taking on each transaction on the debtor account, calculate according to
 percentage.

 @version 0.1
 */
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "commission")
public class Commission {

  /**
   The commission id in the database.
   */
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Getter(AccessLevel.NONE)
  private Long id;

  /**
   The value of commission taking on the transaction.
   */
  @Column(columnDefinition = "numeric (10,2)", nullable = false)
  private double value;

  public Commission(double commissionTransactionValue) {
    this.value=commissionTransactionValue;
  }
}
