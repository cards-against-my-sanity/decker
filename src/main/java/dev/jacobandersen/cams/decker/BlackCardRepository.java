package dev.jacobandersen.cams.decker;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.UUID;

public interface BlackCardRepository extends ListPagingAndSortingRepository<BlackCard, UUID> {
}
