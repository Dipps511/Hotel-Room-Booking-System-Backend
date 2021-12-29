package com.upgrad.BookingService.dao;

import com.upgrad.BookingService.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<TransactionDetailsEntity, Integer> {
}
