package de.ksbrwsk.sse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
class SseRestControllerTest {

    @Autowired
    SseRestController sseRestController;

    @Test
    void timeStream() {
        WebTestClient webTestClient = WebTestClient.bindToController(sseRestController).build();

        String time = webTestClient
                .get()
                .uri("/api/time/stream/500")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .returnResult(String.class)
                .getResponseBody()
                .take(5)
                .blockLast();
        assertNotNull(time);

    }
}