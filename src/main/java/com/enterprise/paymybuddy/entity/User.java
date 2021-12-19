package com.enterprise.paymybuddy.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="users",
uniqueConstraints = @UniqueConstraint(name = "email_unique",columnNames = "email"))
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long userId;

  @Column(columnDefinition = "varchar (100)", nullable = false)
  private String firstName;

  @Column(columnDefinition = "varchar (100)", nullable = false)
  private String lastName;

  @Column(columnDefinition = "varchar (255)", nullable = false)
  private String email;

  @Column(columnDefinition = "varchar (255)", nullable = false)
  private String password;

  @Column(columnDefinition = "varchar (255)", nullable = false)
  private String bankAccount;

  @Column(columnDefinition = "decimal (10,2) default 0")
  private double balance;

  @OneToMany(mappedBy = "debtor",cascade = CascadeType.ALL)
  private List<UserTransaction> userTransactions=new ArrayList<>();

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<BankTransaction> bankTransactions=new ArrayList<>();

  @ManyToMany(targetEntity = User.class)
  private List<User> friends=new ArrayList<>();

  public User(String firstName, String lastName, String email,
              String password, String bankAccount, double balance) {
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
        "userId=" + userId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", bankAccount='" + bankAccount + '\'' +
        ", balance=" + balance +
        '}';
  }
}
