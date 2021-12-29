package com.upgrad.PaymentService.services;

import com.upgrad.PaymentService.entities.TransactionDetailsEntity;

public interface PaymentService {
    public TransactionDetailsEntity completePayment(TransactionDetailsEntity transactionDetailsEntity);

//    public TransactionDetailsEntity completePayment(int bookingId);
    public TransactionDetailsEntity getTransactionDetails(int transactionId);
}
