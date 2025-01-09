package dev.jacobandersen.cams.decker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class CardController {
    private final BlackCardRepository blackCardRepository;
    private final WhiteCardRepository whiteCardRepository;

    public CardController(BlackCardRepository blackCardRepository, WhiteCardRepository whiteCardRepository) {
        this.blackCardRepository = blackCardRepository;
        this.whiteCardRepository = whiteCardRepository;
    }

    @GetMapping("/cards/black")
    public ResponseEntity<PaginatedResult<BlackCard>> blackCards(@RequestParam(required = false, defaultValue = "20") int pageSize, @RequestParam(required = false, defaultValue = "0") int page) {
        if (pageSize > 100) {
            pageSize = 100;
        }

        return ResponseEntity.ok(new PaginatedResult<>(blackCardRepository.findAll(Pageable.ofSize(pageSize).withPage(page))));
    }

    @GetMapping("/cards/white")
    public ResponseEntity<PaginatedResult<WhiteCard>> whiteCards(@RequestParam(required = false, defaultValue = "20") int pageSize, @RequestParam(required = false, defaultValue = "0") int page) {
        if (pageSize > 100) {
            pageSize = 100;
        }

        return ResponseEntity.ok(new PaginatedResult<>(whiteCardRepository.findAll(Pageable.ofSize(pageSize).withPage(page))));
    }
}
