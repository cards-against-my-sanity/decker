package dev.jacobandersen.cams.decker;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class DeckController {
    private final DeckRepository deckRepository;

    public DeckController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @GetMapping("/decks")
    public ResponseEntity<Object> getDecks(@RequestParam(value = "id", required = false) String[] ids) {
        if (ids == null || ids.length == 0) {
            return ResponseEntity.ok(deckRepository.findAll(Sort.by("weight", "name").ascending()));
        } else {
            final List<UUID> parsedIds = Arrays.stream(ids).map(String::trim).map(UUID::fromString).toList();
            return ResponseEntity.ok(deckRepository.findAllById(parsedIds).stream().map(DeckWithCards::new).toList());
        }
    }

    @GetMapping("/deck/{id}")
    public ResponseEntity<DeckWithCards> getDeck(@PathVariable("id") UUID id) {
        final Deck deck = deckRepository.findById(id).orElse(null);

        if (deck == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DeckWithCards(deck));
    }
}
