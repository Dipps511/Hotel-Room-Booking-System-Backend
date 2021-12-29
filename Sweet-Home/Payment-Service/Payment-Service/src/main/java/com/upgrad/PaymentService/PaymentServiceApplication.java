package com.upgrad.PaymentService;

import com.upgrad.PaymentService.dao.TransactionDAO;
import com.upgrad.PaymentService.services.PaymentService;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PaymentServiceApplication.class, args);
		TransactionDAO transactionDAO=context.getBean(TransactionDAO.class);
		PaymentService paymentService=context.getBean(PaymentService.class);
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
