package com.kata.BankAccount;

import com.kata.model.Account;
import com.kata.model.Customer;
import com.kata.repository.AccountRepository;
import com.kata.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = "com.kata.model")
@EnableJpaRepositories({"com.kata.repository"})
@ComponentScan({"com.kata.controller", "com.kata.service"})
public class BankAccountApplication {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(AccountRepository accountRepository, CustomerRepository customerRepository){
		return (args) -> {

			accountRepository.save(new Account(1,10.00, "USD", null, null ));
			accountRepository.save(new Account(2,32.00, "EUR", null, null ));
			logger.info("accounts data has been generated");
		};
	}

}
