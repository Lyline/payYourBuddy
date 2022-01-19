package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 The user repository interface. It's extend to JpaRepository.

 @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  /**
   Gets user by email.

   @param email The user's email hashed
   @return      The user if the email exist else return <i>null</i>
   */
  User getUserByEmail(String email);

  /**
   Gets user by email and password.

   @param email     The email hashed
   @param password  The password hashed
   @return          The user if the email and the password are linked else return Exception
   */
  Optional<User> getUserByEmailAndPassword(String email, String password);
}
