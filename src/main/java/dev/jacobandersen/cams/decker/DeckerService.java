package dev.jacobandersen.cams.decker;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckerService {
    private final DeckRepository deckRepository;

    public DeckerService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Cacheable("decks")
    public List<Deck> getDecks() {
        return deckRepository.findAll(Sort.by("weight", "name").ascending());
    }

    @Cacheable("deckById")
    public Deck getDeckById(UUID id) {
        return deckRepository.findById(id).orElse(null);
    }

    @Cacheable("cardsByDeckIds")
    public Cards getCards(Set<UUID> deckIds) {
        final List<Deck> decks = deckRepository.findAllById(deckIds);
        if (decks.isEmpty()) {
            return null;
        }

        final Set<BlackCard> blackCards = new HashSet<>();
        final Set<WhiteCard> whiteCards = new HashSet<>();

        decks.forEach(deck -> {
            blackCards.addAll(deck.getBlackCards());
            whiteCards.addAll(deck.getWhiteCards());
        });

        return new Cards(blackCards, whiteCards);
    }
}
