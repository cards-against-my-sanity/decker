package dev.jacobandersen.cams.decker;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "black_cards")
public class BlackCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "pick")
    private int pick;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlackCard blackCard)) return false;
        return getPick() == blackCard.getPick() && Objects.equals(getId(), blackCard.getId()) && Objects.equals(getContent(), blackCard.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getPick());
    }
}
