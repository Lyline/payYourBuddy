package com.enterprise.paymybuddy.jpa;

import com.enterprise.paymybuddy.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 The commission repository interface. It's extend to JpaRepository.

 @see Commission
 */
@Repository
public interface CommissionRepository extends JpaRepository<Commission,Long> {
}
