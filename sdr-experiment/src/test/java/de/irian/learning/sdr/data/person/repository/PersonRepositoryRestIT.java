package de.irian.learning.sdr.data.person.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.irian.learning.sdr.SdrExperimentApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdrExperimentApplication.class)
@WebIntegrationTest(randomPort = true)
public class PersonRepositoryRestIT {

	/*
	 * @Autowired private PersonRepository personRepository;
	 */

	@Value("${local.server.port}")
	private int port;

	private RestTemplate restTemplate = new TestRestTemplate();

	private String baseUrl = null;

	@Before
	public void init() {
		baseUrl = String.format("http://localhost:%s/api/v1", port);
	}

	@Test
	public void server_must_run() {
		ResponseEntity<BaseResource> response = restTemplate.exchange(baseUrl, HttpMethod.GET, HttpEntity.EMPTY,
				BaseResource.class);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getLinks()).isNotEmpty();
	}
}

class BaseResource extends ResourceSupport {}
