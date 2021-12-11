package com.enterprise.paymybuddy.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String bankAccount;
  private double balance;

  private List<UserTransaction> userTransactions=new ArrayList<>();
  private List<BankTransaction> bankTransactions=new ArrayList<>();
  private List<User> friends=new ArrayList<>();

  public User(Long id, String firstName, String lastName, String email,
              String password, String bankAccount, double balance) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.bankAccount = bankAccount;
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", bankAccount='" + bankAccount + '\'' +
        ", balance=" + balance +
        '}';
  }
}
