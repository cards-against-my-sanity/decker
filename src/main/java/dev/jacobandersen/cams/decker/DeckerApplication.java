package dev.jacobandersen.cams.decker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DeckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeckerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printDecks(ApplicationReadyEvent event) {
        final ApplicationContext ctx = event.getApplicationContext();
        final DeckRepository repo = ctx.getBean(DeckRepository.class);
        repo.findAll().forEach(deck -> {
            System.out.println(deck.getName());
            System.out.printf("\tWhite cards (%d):%n", deck.getWhiteCards().size());
            deck.getWhiteCards().forEach(wc -> {
                System.out.printf("\t\t%s%n", wc.getContent());
            });
            System.out.printf("\tBlack cards (%d):%n", deck.getBlackCards().size());
            deck.getBlackCards().forEach(bc -> {
                System.out.printf("\t\t%s%n", bc.getContent());
            });
        });
    }

}
