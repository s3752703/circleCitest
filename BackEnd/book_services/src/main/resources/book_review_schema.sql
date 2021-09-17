drop table if exists reviews;
create table reviews
(
    id        bigint
        constraint reviews_pkey
            primary key
        generated always as identity ,
    content   varchar(1000),
    create_at timestamp not null,
    star      integer   not null
        constraint reviews_star_check
            check ((star <= 5) AND (star >= 1)),
    user_id   bigint    not null,
    isbn_fk   varchar(255)
        constraint fkdo60agaf00u6fkj1bih90sqtm
            references books
);

alter table reviews
    owner to postgres;

