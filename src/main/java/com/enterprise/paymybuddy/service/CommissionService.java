package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.Commission;

/**
 The commission service interface.

 @version 0.1

 @see Commission
 */
public interface CommissionService {
  /**
   Calculates the commission taking for a transaction.

   @param transactionValue  The value of transaction
   @return                  The commission object
   */
  Commission calculate(double transactionValue);

  /**
   Saves the commission in the database.

   @param commissionValue The commission object
   @return                The commission object if it's validated
   */
  Commission save(Commission commissionValue);
}
