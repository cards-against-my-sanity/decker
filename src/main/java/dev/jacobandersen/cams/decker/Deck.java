package dev.jacobandersen.cams.decker;

import java.util.UUID;

public record Deck(UUID id, String name, String description, Integer weight) {
}
