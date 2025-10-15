create table decks
(
    id          uuid primary key,
    name        varchar(255)    not null,
    description varchar(255)    not null,
    weight      int default 999 not null
);

create table black_cards
(
    id      uuid primary key,
    content varchar(255) not null,
    pick    int          not null
);

create table black_card_decks
(
    card_id uuid not null,
    deck_id uuid not null,
    primary key (card_id, deck_id),
    foreign key (card_id) references black_cards (id)
        on update cascade on delete cascade,
    foreign key (deck_id) references decks (id)
        on update cascade on delete cascade
);

create table white_cards
(
    id      uuid     not null primary key,
    content varchar(255) not null
);

create table white_card_decks
(
    card_id uuid not null,
    deck_id uuid not null,
    primary key (card_id, deck_id),
    foreign key (card_id) references white_cards (id)
        on update cascade on delete cascade,
    foreign key (deck_id) references decks (id)
        on update cascade on delete cascade
);
