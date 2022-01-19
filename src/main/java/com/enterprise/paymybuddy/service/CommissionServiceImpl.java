package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.jpa.CommissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 The commission service implementation. It's extend to CommissionService.

 @version 0.1

 @see Commission
 */
@Service
public class CommissionServiceImpl implements CommissionService{
  private final CommissionRepository repository;

  public CommissionServiceImpl(CommissionRepository repository) {
    this.repository = repository;
  }

  /**

   @param transactionValue  The value of transaction
   @return                  The commission object with the commission value rounded
   */
  @Override
  public Commission calculate(double transactionValue) {
    double commissionRate=0.5;
    double commissionValue= (transactionValue*commissionRate)/100;

    BigDecimal commissionBD=BigDecimal.valueOf(commissionValue);
    BigDecimal commissionRoundBD=commissionBD.setScale(2, RoundingMode.UP);

    double commissionRound=Double.parseDouble(String.valueOf(commissionRoundBD));

    return new Commission(commissionRound);
  }

  /**

   @param commission The commission object
   @return           The commission object if it's validated, else null
   */
  @Override
  @Transactional
  public Commission save(Commission commission) {
    return repository.save(commission);
  }
}
