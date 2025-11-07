package dev.jacobandersen.cams.decker;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

@Service
public class DeckerService {
    private final DatabaseClient databaseClient;

    private final BiFunction<Row, RowMetadata, Deck> deckRowMapper = (row, meta) -> new Deck(
            row.get("id", UUID.class),
            row.get("name", String.class),
            row.get("description", String.class),
            row.get("weight", Integer.class)
    );

    private final BiFunction<Row, RowMetadata, WhiteCard> whiteCardRowMapper = (row, meta) -> new WhiteCard(
            row.get("id", UUID.class),
            row.get("content", String.class)
    );

    private final BiFunction<Row, RowMetadata, BlackCard> blackCardRowMapper = (row, meta) -> new BlackCard(
            row.get("id", UUID.class),
            row.get("content", String.class),
            row.get("pick", Integer.class)
    );

    @Autowired
    public DeckerService(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Cacheable("decks")
    public Flux<Deck> getDecks() {
        return databaseClient
                .sql("select * from decks order by weight, name")
                .map(deckRowMapper)
                .all();
    }

    @Cacheable("deckDataById")
    public Mono<Deck> getDeckById(UUID id) {
        return databaseClient
                .sql("select * from decks where id = $1 limit 1")
                .bind(0, id)
                .map(deckRowMapper)
                .first();
    }

    @Cacheable("deckExistsById")
    public Mono<Boolean> existsDeckById(UUID id) {
        return databaseClient
                .sql("select exists(select 1 from decks where id = $1 limit 1)")
                .bind(0, id)
                .map((row, meta) -> row.get(0, Boolean.class))
                .first();
    }

    @Cacheable("cardsByDeckId")
    public Mono<Cards> getCards(List<UUID> deckIds) {
        Mono<List<BlackCard>> blackCards = databaseClient
                .sql("select bc.* from black_cards bc inner join black_card_decks bcd on bcd.card_id = bc.id where bcd.deck_id in ($1)")
                .bind(0, deckIds)
                .map(blackCardRowMapper)
                .all()
                .collectList();

        Mono<List<WhiteCard>> whiteCards = databaseClient
                .sql("select wc.* from white_cards wc inner join white_card_decks wcd on wcd.card_id = wc.id where wcd.deck_id in ($1)")
                .bind(0, deckIds)
                .map(whiteCardRowMapper)
                .all()
                .collectList();

        return Mono
                .zip(blackCards, whiteCards)
                .map(tuple -> new Cards(tuple.getT1(), tuple.getT2()));
    }
}
