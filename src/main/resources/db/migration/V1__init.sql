create table decks
(
    id          char(36) primary key,
    name        varchar(255)    not null,
    description varchar(255)    not null,
    weight      int default 999 not null
);

create table black_cards
(
    id      char(36) primary key,
    content varchar(255) not null,
    pick    int          not null
);

create table black_card_decks
(
    card_id char(36) not null,
    deck_id      char(36) not null,
    primary key (card_id, deck_id),
    foreign key (card_id) references black_cards (id)
        on update cascade on delete cascade,
    foreign key (deck_id) references decks (id)
        on update cascade on delete cascade
);

create table white_cards
(
    id      char(36)     not null primary key,
    content varchar(255) not null
);

create table white_card_decks
(
    card_id char(36) not null,
    deck_id      char(36) not null,
    primary key (card_id, deck_id),
    foreign key (card_id) references white_cards (id)
        on update cascade on delete cascade,
    foreign key (deck_id) references decks (id)
        on update cascade on delete cascade
);
