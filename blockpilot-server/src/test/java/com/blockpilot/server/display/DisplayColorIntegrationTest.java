package com.blockpilot.server.display;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
@Tag("integration")
class DisplayColorIntegrationTest {

    @LocalServerPort
    private int port;

    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void setColor_red() {
        assertThat(post("FF0000").getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void setColor_green() {
        assertThat(post("00FF00").getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void setColor_blue() {
        assertThat(post("0000FF").getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void setColor_off() {
        assertThat(post("off").getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<Void> post(String color) {
        return restClient.post()
            .uri("/api/display/color")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ColorRequest(color))
            .retrieve()
            .toBodilessEntity();
    }
}
