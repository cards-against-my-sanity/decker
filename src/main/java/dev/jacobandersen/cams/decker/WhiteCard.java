package dev.jacobandersen.cams.decker;

import java.io.Serializable;
import java.util.UUID;

public record WhiteCard(UUID id, String content) implements Serializable {
}
