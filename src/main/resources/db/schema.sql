create table if not exists Taco_Order
(
    id              bigint primary key not null,
    delivery_name   varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city   varchar(50) not null,
    delivery_state  varchar(2)  not null,
    delivery_zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null
);
create table if not exists Taco
(
    id             bigint primary key not null,
    taco_name      varchar(50) not null,
    taco_order     bigint      not null references Taco_Order(id),
    created_at     timestamp   not null
);

create table if not exists Ingredient
(
    id   varchar(4)  primary key not null,
    ingredient_name varchar(25) not null,
    ingredient_type varchar(10) not null
);