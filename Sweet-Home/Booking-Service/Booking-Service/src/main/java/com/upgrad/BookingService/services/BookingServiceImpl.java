package com.upgrad.BookingService.services;

import com.upgrad.BookingService.dao.BookingDAO;
import com.upgrad.BookingService.dto.TransactionDTO;
import com.upgrad.BookingService.entities.BookingInfoEntity;
import com.upgrad.BookingService.entities.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDAO bookingDAO;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfoEntity) {
        bookingInfoEntity.setBookedOn(bookedOn(bookingInfoEntity));
        return bookingDAO.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity getBooking(int bookingId) {
        return bookingDAO.getById(bookingId);
    }

    @Override
    public BookingInfoEntity updateBookingDetails(int bookingId, BookingInfoEntity bookingInfoEntity) {
        BookingInfoEntity bookingInfoEntity1 = bookingDAO.getById(bookingId);
        bookingInfoEntity1.setFromDate(bookingInfoEntity.getFromDate());
        bookingInfoEntity1.setToDate(bookingInfoEntity.getToDate());
        bookingInfoEntity1.setAadharNumber(bookingInfoEntity.getAadharNumber());
        bookingInfoEntity1.setNumOfRooms(bookingInfoEntity.getNumOfRooms());
        return bookingDAO.save(bookingInfoEntity1);

    }


    @Override
    public BookingInfoEntity saveToDate(LocalDate toDate) {
        return null;
    }

    @Override
    public LocalDate saveFromDate(LocalDate fromDate) {
        return null;
    }

    @Override
    public String saveAadharNumber(String aadharNumber)  {
        return null;
    }

    @Override
    public int numOfRooms(int numOfRooms) {
        return 0;
    }

    @Override
    public String getListOfRooms(BookingInfoEntity bookingInfoEntity) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<bookingInfoEntity.getNumOfRooms(); i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        bookingInfoEntity.setRoomNumbers(Arrays.toString(numberList.toArray()).replace("[","").replace("]",""));
        return numberList.toString().replace("[","").replace("]","");
    }

    @Override
    public LocalDate bookedOn(BookingInfoEntity bookingInfoEntity) {
        LocalDate bookedOnDate = LocalDate.now();
        return bookedOnDate;
    }

    @Override
    public int numOfDays(BookingInfoEntity bookingInfoEntity) {

        if(bookingInfoEntity.getToDate().getDayOfYear() - bookingInfoEntity.getFromDate().getDayOfYear() <= 0 && bookingInfoEntity.getToDate().getYear() <= bookingInfoEntity.getFromDate().getYear()){
            throw new IllegalArgumentException("Start date cannot be after or same as the end date");
        }
        else if(bookingInfoEntity.getToDate().getDayOfYear() - bookingInfoEntity.getFromDate().getDayOfYear() < 0 && bookingInfoEntity.getToDate().getYear() > bookingInfoEntity.getFromDate().getYear()){
            return bookingInfoEntity.getToDate().getDayOfYear() > bookingInfoEntity.getFromDate().getDayOfYear() ? bookingInfoEntity.getToDate().getDayOfYear() - bookingInfoEntity.getFromDate().getDayOfYear(): ((bookingInfoEntity.getToDate().getDayOfYear()+ LocalDate.of(bookingInfoEntity.getFromDate().getYear(),12,31).getDayOfYear())- bookingInfoEntity.getFromDate().getDayOfYear());
        }
        return bookingInfoEntity.getToDate().getDayOfYear() - bookingInfoEntity.getFromDate().getDayOfYear();
    }

    @Override
    public int computeTotalPrice(BookingInfoEntity bookingInfoEntity){
        bookingInfoEntity.setRoomPrice(1000* bookingInfoEntity.getNumOfRooms()*numOfDays(bookingInfoEntity));
        getListOfRooms(bookingInfoEntity);
        return bookingInfoEntity.getRoomPrice();
    }

    @Override
    public int makePayment(int bookingId, TransactionDetailsEntity transactionDetailsEntity) {
        Map<String,String> transactionDetailsEntityUriMap = new HashMap<>();
        System.out.println(transactionDetailsEntity.getPaymentMode());
        transactionDetailsEntityUriMap.put("paymentMode",String.valueOf(transactionDetailsEntity.getPaymentMode()));
        transactionDetailsEntityUriMap.put("bookingId",String.valueOf(bookingId));
        transactionDetailsEntityUriMap.put("upiId",String.valueOf(transactionDetailsEntity.getUpiId()));
        transactionDetailsEntityUriMap.put("cardNumber",String.valueOf(transactionDetailsEntity.getCardNumber()));
        String PaymentAppUri = "http://localhost:8083/payment/transaction";

        int receivedTransactionId = (restTemplate.getForObject(PaymentAppUri, TransactionDetailsEntity.class, transactionDetailsEntityUriMap)).getTransactionId();

        return receivedTransactionId;
    }
}
