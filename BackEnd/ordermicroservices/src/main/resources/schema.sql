drop table if exists order_line_item;
drop table if exists orders;
create table orders
(
    id          bigserial
        constraint orders_pkey
            primary key,
    create_at   timestamp not null,
    customer_id varchar(255) not null,
    paid        boolean not null,
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

