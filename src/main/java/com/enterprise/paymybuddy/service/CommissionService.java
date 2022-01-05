package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.Commission;

public interface CommissionService {
  Commission calculate(double transactionValue);
  Commission save(Commission commissionValue);
}
