package com.enterprise.paymybuddy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "commission")
public class Commission {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Getter(AccessLevel.NONE)
  private Long id;

  @Column(columnDefinition = "numeric (10,2)", nullable = false)
  private double value;

  public Commission(double commissionTransactionValue) {
    this.value=commissionTransactionValue;
  }
}
