package com.secretsanta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretsanta.model.Person;
import com.secretsanta.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Optional<Person> findById(String id) {
		return personRepository.findById(id);
	}
	
	public Optional<Person> findByName(String name) {
		return personRepository.findByName(name);
	}	

	public Person create(Person person) {
		return personRepository.save(person);
	}

	public Person update(String id, Person person) {

		Optional<Person> personOptional = personRepository.findById(id);

		if (personOptional.isPresent()) {
			Person personUpdate = personOptional.get();
			personUpdate.setName(person.getName());
			personUpdate.setEmail(person.getEmail());
			personUpdate.setFriend(person.getFriend());
			return personRepository.save(personUpdate);
		} else {
			return null;
		}
	}

	public String deleteBy(String id) {

		personRepository.deleteById(id);

		return "A person foi excluída!";
	}

	public String deleteAll() {

		personRepository.deleteAll();

		return "Todas as persons foram excluídas!";
	}

}
