package com.upgrad.PaymentService.controller;

import com.upgrad.PaymentService.dto.TransactionDTO;
import com.upgrad.PaymentService.entities.TransactionDetailsEntity;
import com.upgrad.PaymentService.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@RestController
@RequestMapping("/payment")
public class TransactionController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    ModelMapper modelMapper;

//    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@RequestBody TransactionDetailsEntity transactionDetailsEntity) {
////        System.out.println(transactionDTO.getPaymentMode());
////        TransactionDetailsEntity transactionDetailsEntity = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);
//        System.out.println(transactionDetailsEntity.getPaymentMode());
//        TransactionDetailsEntity transactionDetailsEntity2= paymentService.completePayment(transactionDetailsEntity);
//
////        int transactionId = paymentService.completePayment(transactionDetailsEntity);
//
//        return new ResponseEntity(transactionDetailsEntity2, HttpStatus.CREATED);
//    }

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable(name = "bookingId") int bookingId,@RequestBody TransactionDTO transactionDTO) {
//        System.out.println(transactionDTO.getPaymentMode());
//        TransactionDetailsEntity transactionDetailsEntity = paymentService.completePayment(bookingId);
        TransactionDetailsEntity transactionDetailsEntity = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);
//        System.out.println(cardNumber);
//        System.out.println(paymentMode);
//        TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();
        transactionDetailsEntity.setBookingId(bookingId);
        transactionDetailsEntity.setPaymentMode(transactionDTO.getPaymentMode());
        transactionDetailsEntity.setCardNumber(transactionDetailsEntity.getCardNumber());
//        transactionDetailsEntity.setPaymentMode(paymentMode);
//        transactionDetailsEntity.setUpiId(upiId);
//        transactionDetailsEntity.setCardNumber(cardNumber);
        System.out.println(transactionDetailsEntity.getPaymentMode());
        System.out.println(transactionDetailsEntity.getBookingId());
        TransactionDetailsEntity transactionDetailsEntity2= paymentService.completePayment(transactionDetailsEntity);

//        int transactionId = paymentService.completePayment(transactionDetailsEntity);

        return new ResponseEntity(transactionDetailsEntity2, HttpStatus.CREATED);
    }





    @GetMapping(value = "/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable("transactionId") int transactionId) {
        TransactionDetailsEntity transactionDetailsEntity = paymentService.getTransactionDetails(transactionId);
        return new ResponseEntity(transactionDetailsEntity, HttpStatus.OK);
    }

}
