package dev.jacobandersen.cams.decker;

import java.io.Serializable;
import java.util.List;

public record Cards(List<BlackCard> blackCards, List<WhiteCard> whiteCards) implements Serializable {
}
