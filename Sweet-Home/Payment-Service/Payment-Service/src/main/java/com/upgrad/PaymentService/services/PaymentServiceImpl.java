package com.upgrad.PaymentService.services;

import com.upgrad.PaymentService.dao.TransactionDAO;
import com.upgrad.PaymentService.entities.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    RestTemplate restTemplate;
//
//    @Override
//    public TransactionDetailsEntity completePayment(TransactionDetailsEntity transactionDetailsEntity) {
//        return transactionDAO.save(transactionDetailsEntity);
//    }


    @Override
    public TransactionDetailsEntity completePayment(TransactionDetailsEntity transactionDetailsEntity) {
        return transactionDAO.save(transactionDetailsEntity);
    }

    @Override
    public TransactionDetailsEntity getTransactionDetails(int transactionId) {
        return transactionDAO.findById(transactionId).get();
    }

}
