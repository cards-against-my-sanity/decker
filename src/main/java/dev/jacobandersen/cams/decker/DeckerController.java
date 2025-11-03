package dev.jacobandersen.cams.decker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class DeckerController {
    private final DeckerService deckerService;

    public DeckerController(DeckerService deckerService) {
        this.deckerService = deckerService;
    }

    @GetMapping("/decks")
    public Flux<Deck> getDecks() {
        return deckerService.getDecks();
    }

    @GetMapping("/decks/{id}")
    public Mono<ResponseEntity<Deck>> getDeck(@PathVariable("id") UUID id) {
        return deckerService
                .getDeckById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/cards")
    public Mono<Cards> getCardsInDecks(@RequestParam("id") UUID[] ids) {
        return deckerService.getCards(Arrays.asList(ids));
    }
}
