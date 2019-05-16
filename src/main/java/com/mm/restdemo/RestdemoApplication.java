package com.mm.restdemo;

import com.mm.restdemo.model.Person;
import com.mm.restdemo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import java.math.BigDecimal;
import java.time.LocalDate;

@EnableRetry
@SpringBootApplication
public class RestdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PersonRepository personRepository) {
		return args -> {

			Person person = new Person();
			person.setFirstName("Mike");
			person.setLastName("Miller");
			person.setDob( LocalDate.of(1959, 12, 11));
			person.setSalary(new BigDecimal(90000.00));
			personRepository.save(person);

			Person rest = new Person();
			rest.setFirstName("Josh");
			rest.setLastName("Long");
			rest.setDob(LocalDate.of (1980, 1, 1));
			rest.setSalary(new BigDecimal(500000.00));
			personRepository.save(rest);

			Person rest1 = new Person();
			rest1.setFirstName("Jonathan");
			rest1.setLastName("FilsAime");
			rest1.setDob(LocalDate.of (1995, 7, 1));
			rest1.setSalary(new BigDecimal(50000.00));
			personRepository.save(rest1);

			Person rest2 = new Person();
			rest2.setFirstName("Tyler");
			rest2.setLastName("Davis");
			rest2.setDob(LocalDate.of (2000, 12, 1));
			rest2.setSalary(new BigDecimal(50000.00));
			personRepository.save(rest2);

		};
	}


}
