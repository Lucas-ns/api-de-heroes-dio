package com.digitalinnovationone.heroesapi;

import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebClient
@SpringBootTest
class HeroesapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroById() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroNotFound(){

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isNotFound();
	}

	public void deleteHero(){

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}
}
