package com.upgrad.BookingService;

import com.upgrad.BookingService.dao.BookingDAO;
import com.upgrad.BookingService.entities.BookingInfoEntity;
import com.upgrad.BookingService.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@RestController
@SpringBootApplication
public class BookingServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookingServiceApplication.class, args);

		BookingDAO bookingDAO = context.getBean(BookingDAO.class);



		BookingService bookingService = context.getBean(BookingService.class);

//		BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();
//		bookingInfoEntity.setBookingId(1);
//		bookingInfoEntity.setFromDate(LocalDate.of(2021, 12, 30));
//		bookingInfoEntity.setToDate(LocalDate.of(2022, 1, 5));
//		bookingInfoEntity.setNumOfRooms(5);
//		bookingInfoEntity.setAadharNumber("123456789012");
//		bookingInfoEntity.setNumOfRooms(2);
//		bookingService.createBooking(bookingInfoEntity);
//		System.out.println(bookingService.computeTotalPrice(bookingInfoEntity));
//		System.out.println(bookingService.getListOfRooms(bookingInfoEntity));
//		System.out.println(bookingService.numOfDays(bookingInfoEntity));
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
