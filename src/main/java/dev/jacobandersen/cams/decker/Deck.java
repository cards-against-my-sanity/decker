package dev.jacobandersen.cams.decker;

import java.io.Serializable;
import java.util.UUID;

public record Deck(UUID id, String name, String description, Integer weight) implements Serializable {
}
