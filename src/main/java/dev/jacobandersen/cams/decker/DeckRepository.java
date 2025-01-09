package dev.jacobandersen.cams.decker;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.UUID;

public interface DeckRepository extends ListCrudRepository<Deck, UUID>, ListPagingAndSortingRepository<Deck, UUID> {

}
