package dev.jacobandersen.cams.decker;

import java.util.List;

public record DeckWithCards(Deck deck, List<BlackCard> blackCards, List<WhiteCard> whiteCards) {
    public DeckWithCards(Deck deck) {
        this(deck, deck.getBlackCards(), deck.getWhiteCards());
    }
}
