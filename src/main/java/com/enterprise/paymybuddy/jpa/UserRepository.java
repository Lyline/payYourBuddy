package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  User getUserByEmail(String email);
  Optional<User> getUserByEmailAndPassword(String email, String password);
}
