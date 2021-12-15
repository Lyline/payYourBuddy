package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.User;

import java.util.Optional;

public interface UserService {
  Optional<User>getUser(Long id);
}
