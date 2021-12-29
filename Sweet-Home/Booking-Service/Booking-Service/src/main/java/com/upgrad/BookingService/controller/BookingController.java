package com.upgrad.BookingService.controller;

import com.upgrad.BookingService.dto.BookingDTO;
import com.upgrad.BookingService.dto.TransactionDTO;
import com.upgrad.BookingService.entities.BookingInfoEntity;
import com.upgrad.BookingService.entities.TransactionDetailsEntity;
import com.upgrad.BookingService.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value="/booking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingDTO bookingDTO) {
//        LocalDate fromDate = LocalDate.of(2022, 1, 1);
//        LocalDate toDate = LocalDate.of(2022, 1, 6);
//        bookingDTO.setFromDate(fromDate);
//        bookingDTO.setToDate(toDate);
//        bookingDTO.setNumOfRooms(3);
//        bookingDTO.setAadharNumber("12345678912345");

        BookingInfoEntity newBooking = modelMapper.map(bookingDTO, BookingInfoEntity.class);
//        System.out.println(newBooking.getNumOfRooms());
        bookingService.computeTotalPrice(newBooking);
        bookingService.getListOfRooms(newBooking);
//        System.out.println(newBooking);
        BookingInfoEntity savedBooking = bookingService.createBooking(newBooking);
//        System.out.println(savedBooking);
        BookingDTO savedBookingDTO = modelMapper.map(savedBooking, BookingDTO.class);
//        System.out.println(savedBookingDTO);
        return new ResponseEntity(savedBookingDTO, HttpStatus.CREATED);
    }

    @PostMapping(value="booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getTransactionId(@PathVariable("bookingId") int bookingId, @RequestBody TransactionDTO transactionDTO) {


        TransactionDetailsEntity transactionDetailsEntity = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);

        int transactionId= bookingService.makePayment(bookingId, transactionDetailsEntity);
        return new ResponseEntity(transactionId, HttpStatus.OK);
    }




}
