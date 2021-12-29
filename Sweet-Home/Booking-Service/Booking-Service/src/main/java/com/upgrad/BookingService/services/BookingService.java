package com.upgrad.BookingService.services;

import com.upgrad.BookingService.entities.BookingInfoEntity;
import com.upgrad.BookingService.entities.TransactionDetailsEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface BookingService {

    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfoEntity);

    public BookingInfoEntity getBooking(int bookingId);

    public BookingInfoEntity updateBookingDetails(int bookingId, BookingInfoEntity bookingInfoEntity);

    public BookingInfoEntity saveToDate(LocalDate toDate);

    public LocalDate saveFromDate(LocalDate fromDate);

    public String saveAadharNumber(String aadharNumber);

    public int numOfRooms(int numOfRooms);

    public String getListOfRooms(BookingInfoEntity bookingInfoEntity);

    public LocalDate bookedOn(BookingInfoEntity bookingInfoEntity);

    public int numOfDays(BookingInfoEntity bookingInfoEntity);

    public int computeTotalPrice(BookingInfoEntity bookingInfoEntity);

    public int makePayment(int transactionId,TransactionDetailsEntity transactionDetailsEntity);

}
