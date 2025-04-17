create sequence user_id_seq start with 100 increment by 50;

create table users
(
    id         bigint       not null default nextval('user_id_seq'),
    user_uuid  varchar(50)  not null unique,
    email      varchar(255) not null unique,
    full_name  varchar(255) not null,
    role       varchar(255) not null default 'ROLE_USER',
    created_at timestamp    not null default current_timestamp,
    updated_at timestamp,
    primary key (id)
);
