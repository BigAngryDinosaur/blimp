package com.bigangrydinosaur.blimp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun `url creation`() {
        val url = "http://www.reddit.com"
        webTestClient
                .post()
                .uri("/api/url/")
                .bodyValue(url)
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.code").isNotEmpty
                .jsonPath("$.url").isEqualTo(url)

    }
}