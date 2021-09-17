drop table if exists order_line_item;
drop table if exists orders;
drop table if exists payments;
drop table if exists  listings;

create table listings
(
    id           bigserial
        constraint listings_pkey
            primary key,
    condition    varchar(255),
    create_at    timestamp,
    is_publisher boolean not null,
    is_sold      boolean not null,
    isbn         varchar(255),
    order_id     varchar(255),
    price        real    not null,
    update_at    timestamp,
    user_id      varchar(255)
);

alter table listings
    owner to postgres;

create table payments
(
    id          bigserial
        constraint payments_pkey
            primary key,
    create_at   timestamp,
    currency    varchar(255),
    description varchar(255),
    intent      varchar(255),
    method      varchar(255),
    price       double precision not null,
    update_at   timestamp
);

alter table payments
    owner to postgres;

create table orders
(
    id          bigserial
        constraint orders_pkey
            primary key,
    create_at   timestamp not null,
    user_id varchar(255) not null,
    payment_id  varchar(255),
    status      varchar(255) not null,
    update_at   timestamp
);

alter table orders
    owner to postgres;

-- auto-generated definition
create table order_line_item
(
    id                bigserial
        constraint order_line_item_pkey
            primary key,
    isbn              varchar(255) not null,
    net_amount        double precision not null,
    order_line_number integer          not null,
    tax_amount        double precision not null,
    total_amount      double precision not null,
    order_id          bigint
        constraint fk_orders
            references orders
);

alter table order_line_item
    owner to postgres;