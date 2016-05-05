package de.irian.learning.sdr.data.person.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.irian.learning.sdr.SdrExperimentApplication;
import de.irian.learning.sdr.data.person.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdrExperimentApplication.class)
public class PersonRepositoryIT {

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void personRepository_must_exist() {
		assertThat(personRepository).isNotNull();
	}
	
	@Test
	public void personRepository_must_findAll() {
		Iterable<Person> persons = personRepository.findAll();
		assertThat(persons).isNotEmpty();
	}
	
	@Test
	public void personRepository_must_find() {
		Person person = personRepository.findOne(1);
		assertThat(person).isNotNull();
	}
}
