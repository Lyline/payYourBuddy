package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;

public interface ConnexionService {
  User connect(UserConnexionDTO logging);
}
