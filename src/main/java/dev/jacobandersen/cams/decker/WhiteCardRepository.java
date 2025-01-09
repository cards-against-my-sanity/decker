package dev.jacobandersen.cams.decker;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.UUID;

public interface WhiteCardRepository extends ListPagingAndSortingRepository<WhiteCard, UUID> {
}
