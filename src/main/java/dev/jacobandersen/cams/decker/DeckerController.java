package dev.jacobandersen.cams.decker;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Controller
public class DeckerController {
    private final DeckerService deckerService;

    public DeckerController(DeckerService deckerService) {
        this.deckerService = deckerService;
    }

    @GetMapping("/decks")
    public ResponseEntity<List<Deck>> getDecks() {
        return ResponseEntity.ok(deckerService.getDecks());
    }

    @GetMapping("/decks/{id}")
    public ResponseEntity<Deck> getDeck(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(deckerService.getDeckById(id));
    }

    @GetMapping("/cards")
    public ResponseEntity<Cards> getCardsInDecks(@RequestParam("id") UUID[] ids) {
        final Cards cards = deckerService.getCards(new HashSet<>(Arrays.asList(ids)));
        if (cards == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cards);
    }
}
