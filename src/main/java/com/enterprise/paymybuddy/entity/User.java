package com.enterprise.paymybuddy.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 Defines the user's object, data object and the links with user's and bank's transactions and friend's list.

 @version 0.1
 @see com.enterprise.paymybuddy.dto.UserDTO
 @see com.enterprise.paymybuddy.dto.UserConnexionDTO
 @see com.enterprise.paymybuddy.dto.RegisterDTO
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name="users",
uniqueConstraints = @UniqueConstraint(name = "email_unique",columnNames = "email"))
public class User {

  /**
   The user id in the database.
   */
  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
          @org.hibernate.annotations.Parameter(name = "initial_value", value = "100")
         }
  )
  @Column(name = "user_id")
  private Long userId;

  /**
   The first name of the user.
   */
  @Column(columnDefinition = "varchar (100)", nullable = false)
  private String firstName;

  /**
   The last name of the user.
   */
  @Column(columnDefinition = "varchar (100)", nullable = false)
  private String lastName;

  /**
   The email of the user for connect to application.
   */
  @Column(columnDefinition = "varchar (255)", nullable = false)
  private String email;

  /**
   The password of the user for connect to application.
   */
  @Column(columnDefinition = "varchar (255)", nullable = false)
  @Setter(AccessLevel.NONE)
  private String password;

  /**
   The bank account number of the user.
   */
  @Column(columnDefinition = "varchar (255)", nullable = false)
  private String bankAccount;

  /**
   The balance of user's account in the application.
   */
  @Column(columnDefinition = "decimal (10,2) default 0")
  private double balance;

  /**
   The list of user's transactions with his friends. They group by the debit transactions of user. It's a list of
   UserTransaction.

   @see UserTransaction
   */
  @OneToMany(mappedBy = "debtor", cascade = CascadeType.ALL)
  private List<UserTransaction> userTransactions = new ArrayList<>();

  /**
   The list of transactions with the user's bank : send and receive money. It's a list of BankTransaction.

   @see BankTransaction
   */
  @OneToMany(mappedBy = "debtor",cascade = {CascadeType.ALL})
  private List<BankTransaction> bankTransactions = new ArrayList<>();

  /**
   The friend's list of user. It's a list of User
   */
  @ManyToMany(targetEntity = User.class)
  @Setter(AccessLevel.NONE)
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
}
