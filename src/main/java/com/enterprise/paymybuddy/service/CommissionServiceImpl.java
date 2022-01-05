package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.jpa.CommissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CommissionServiceImpl implements CommissionService{
  private final CommissionRepository repository;

  public CommissionServiceImpl(CommissionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Commission calculate(double transactionValue) {
    double commissionRate=0.5;
    double commissionValue= (transactionValue*commissionRate)/100;

    BigDecimal commissionBD=BigDecimal.valueOf(commissionValue);
    BigDecimal commissionRoundBD=commissionBD.setScale(2, RoundingMode.UP);

    double commissionRound=Double.parseDouble(String.valueOf(commissionRoundBD));

    return new Commission(commissionRound);
  }

  @Override
  @Transactional
  public Commission save(Commission commission) {
    return repository.save(commission);
  }
}
