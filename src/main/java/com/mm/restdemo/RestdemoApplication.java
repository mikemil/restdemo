package com.mm.restdemo;

import com.mm.restdemo.model.Person;
import com.mm.restdemo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

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
			person.setSalary(new BigDecimal(900000.00));
			personRepository.save(person);

			Person rest = new Person();
			rest.setFirstName("Josh");
			rest.setLastName("Long");
			rest.setDob(LocalDate.of (1980, 1, 1));
			rest.setSalary(new BigDecimal(500000.00));
			personRepository.save(rest);

		};
	}


}
