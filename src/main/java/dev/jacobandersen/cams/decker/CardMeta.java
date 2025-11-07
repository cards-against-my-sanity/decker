package dev.jacobandersen.cams.decker;

import java.io.Serializable;

public record CardMeta(int totalBlackCards, int totalWhiteCards) implements Serializable {
}
