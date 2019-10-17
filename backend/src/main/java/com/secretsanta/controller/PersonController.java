package com.secretsanta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretsanta.model.Person;
import com.secretsanta.service.PersonService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/people")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping
	public List<Person> getPeople() {
		return personService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findPersonById(@PathVariable String id) {

		Optional<Person> personOptional = personService.findById(id);

		if (personOptional.isPresent()) {
			return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Person> findPersonByName(@PathVariable String name) {

		Optional<Person> personOptional = personService.findByName(name);

		if (personOptional.isPresent()) {
			return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	

	@PostMapping
	public Person createPerson(@RequestBody Person person) {
		return personService.create(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person) {

		Person personUpdate = personService.update(id, person);

		if (personUpdate != null) {
			return new ResponseEntity<>(personUpdate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable("id") String id) {

		String tBody = personService.deleteBy(id);

		return new ResponseEntity<>(tBody, HttpStatus.OK);
	}

	@DeleteMapping("/delete-all")
	public ResponseEntity<String> deleteAllPersons() {

		String tBody = personService.deleteAll();

		return new ResponseEntity<>(tBody, HttpStatus.OK);
	}

}
