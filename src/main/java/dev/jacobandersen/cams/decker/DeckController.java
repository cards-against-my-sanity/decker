package dev.jacobandersen.cams.decker;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Controller
public class DeckController {
    private final DeckRepository deckRepository;

    public DeckController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @GetMapping("/decks")
    public ResponseEntity<List<Deck>> getDecks() {
        return ResponseEntity.ok(deckRepository.findAll(Sort.by("weight", "name").ascending()));
    }

    @GetMapping("/deck/{id}")
    public ResponseEntity<Deck> getDeck(@PathVariable("id") UUID id) {
        final Deck deck = deckRepository.findById(id).orElse(null);

        if (deck == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deck);
    }
}
