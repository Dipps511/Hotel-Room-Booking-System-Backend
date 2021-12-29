package com.upgrad.BookingService.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDTO {
    private int bookingId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String aadharNumber;

    private String roomNumbers;

    private int numOfRooms;

    private int roomPrice;

    private int transactionId = 0;

    private LocalDate bookedOn;

}
