package com.enterprise.paymybuddy.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "commission")
public class Commission {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(columnDefinition = "numeric (10,2)", nullable = false)
  private double value;

  public Commission(double commissionTransactionValue) {
    this.value=commissionTransactionValue;
  }
}
