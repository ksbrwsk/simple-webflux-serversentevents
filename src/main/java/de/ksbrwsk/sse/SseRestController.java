package de.ksbrwsk.sse;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@Log4j2
public class SseRestController {

    @GetMapping("api/time/stream/{interval}")
    Flux<ServerSentEvent<String>> time(@PathVariable int interval) {
        return Flux.interval(Duration.ofMillis(interval))
                .map(seq -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(seq))
                        .comment("current server time")
                        .data(LocalDateTime.now().toString())
                        .build())
                .doOnEach(log::info);
    }
}
