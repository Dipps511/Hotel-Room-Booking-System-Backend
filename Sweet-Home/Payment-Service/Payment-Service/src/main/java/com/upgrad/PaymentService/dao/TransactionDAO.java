package com.upgrad.PaymentService.dao;

import com.upgrad.PaymentService.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<TransactionDetailsEntity, Integer> {
}
