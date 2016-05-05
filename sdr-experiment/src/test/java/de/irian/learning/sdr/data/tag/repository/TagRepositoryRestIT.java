package de.irian.learning.sdr.data.tag.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.TypeReferences.ResourceType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import de.irian.learning.sdr.SdrExperimentApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
@WebIntegrationTest(randomPort = true)
public class TagRepositoryRestIT {

	@SpringBootApplication
	@Import(SdrExperimentApplication.class)
	static class Config {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}	

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private RestTemplate restTemplate;

	private final String BASE_URL = "http://localhost:%s/api/v1";

	@Test
	public void root_must_expose_tagRepository() throws InterruptedException {
		URI uri = URI.create(String.format(BASE_URL, port));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaTypes.HAL_JSON).build();

		Resource<Object> rootLinks = restTemplate.exchange(request, new ResourceType<Object>() {
		}).getBody();

		assertThat(rootLinks).isNotNull();
		assertThat(rootLinks.getLinks()).isNotEmpty();

		assertThat(rootLinks.getLink("tags")).isNotNull();
	}
	
	
	@Test
	public void tagRepository_must_expose_findAll() {
		URI uri = URI.create(String.format(BASE_URL + "/tags", port));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaTypes.HAL_JSON).build();

		Resource<Object> rootLinks = restTemplate.exchange(request, new ResourceType<Object>() {
		}).getBody();

		assertThat(rootLinks).isNotNull();
		assertThat(rootLinks.getLinks()).isNotEmpty();

		assertThat(rootLinks.getLink("self")).isNotNull();
	}
	
	
	@Test
	public void tagRepository_must_expose_findOne() {
		URI uri = URI.create(String.format(BASE_URL + "/tags/1", port));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaTypes.HAL_JSON).build();

		Resource<Object> rootLinks = restTemplate.exchange(request, new ResourceType<Object>() {
		}).getBody();

		assertThat(rootLinks).isNotNull();
		assertThat(rootLinks.getLinks()).isNotEmpty();

		assertThat(rootLinks.getLink("self")).isNotNull();
		assertThat(rootLinks.getLink("tag")).isNotNull();
	}
}