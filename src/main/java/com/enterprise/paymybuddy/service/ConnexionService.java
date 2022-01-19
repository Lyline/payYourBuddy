package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserConnexionDTO;
import com.enterprise.paymybuddy.entity.User;

/**
 The connexion service interface.

 @version 0.1

 @see User
 @see UserConnexionDTO
 */
public interface ConnexionService {
  /**
   Connects the user to the application

   @param logging The connexion attributes : email and password of the user
   @return        The user if it's exist, else null
   */
  User connect(UserConnexionDTO logging);
}
