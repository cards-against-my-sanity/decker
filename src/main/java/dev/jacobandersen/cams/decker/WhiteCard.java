package dev.jacobandersen.cams.decker;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "white_cards")
public class WhiteCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "content")
    private String content;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WhiteCard whiteCard)) return false;
        return Objects.equals(getId(), whiteCard.getId()) && Objects.equals(getContent(), whiteCard.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent());
    }
}
