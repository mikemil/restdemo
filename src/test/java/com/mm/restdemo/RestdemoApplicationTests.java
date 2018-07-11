package com.mm.restdemo;

import com.mm.restdemo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestdemoApplicationTests {

	static String PERSON_RESOURCE_URL = "http://localhost:8080/person";

	@Test
	public void testGetById() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Person> response = testRestTemplate.
				getForEntity(PERSON_RESOURCE_URL + "/1", Person.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testGetAll() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Person[]> response = testRestTemplate.
				getForEntity(PERSON_RESOURCE_URL + "/", Person[].class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testCreate() throws URISyntaxException {
		TestRestTemplate template = new TestRestTemplate();
		Person body = new Person();
		body.setLastName("Gosling");
		body.setFirstName("James");
		body.setDob(LocalDate.of(1950, 12, 25));
		body.setSalary(new BigDecimal("999999.00"));
		RequestEntity request = RequestEntity.post(new URI("http://localhost:8080/person/")).accept(MediaType.APPLICATION_JSON).body(body);
		ResponseEntity<Person> response = template.exchange(request, Person.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testCreateThatFailsBeanValidation() throws URISyntaxException {
		TestRestTemplate template = new TestRestTemplate();
		Person body = new Person();
		body.setLastName("Gosling");
		body.setDob(LocalDate.of(2100, 12, 25));
		body.setSalary(new BigDecimal("999999999.00"));
		RequestEntity request = RequestEntity.post(new URI("http://localhost:8080/person/")).accept(MediaType.APPLICATION_JSON).body(body);
		ResponseEntity<Person> response = template.exchange(request, Person.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	}
}
