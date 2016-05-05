package de.irian.learning.sdr.data.tag.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.irian.learning.sdr.SdrExperimentApplication;
import de.irian.learning.sdr.data.tag.entity.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdrExperimentApplication.class)
public class TagRepositoryIT {

	@Autowired
	private TagRepository tagRepository;
	
	@Test
	public void tagRepository_must_exist() {
		assertThat(tagRepository).isNotNull();
	}
	
	@Test
	public void tagRepository_must_findAll() {
		Iterable<Tag> tags = tagRepository.findAll();
		assertThat(tags).isNotEmpty();
	}
	
	@Test
	public void tagRepository_must_find() {
		Tag tag = tagRepository.findOne(1);
		assertThat(tag).isNotNull();
	}
	
}
