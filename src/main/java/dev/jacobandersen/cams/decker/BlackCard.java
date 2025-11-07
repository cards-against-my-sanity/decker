package dev.jacobandersen.cams.decker;

import java.io.Serializable;
import java.util.UUID;

public record BlackCard(UUID id, String content, Integer pick) implements Serializable {
}