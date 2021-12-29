package com.upgrad.BookingService.dto;

import lombok.Data;

@Data
public class TransactionDTO {

    private int transactionId;

    private String paymentMode;

    private int bookingId;

    private String upiId;

    private String cardNumber;
}
