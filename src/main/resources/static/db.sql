create table users
(
    id            bigint primary key generated always as identity,
    username      text not null unique,
    email         text not null unique,
    password_hash text not null,
    created_at    timestamp with time zone default now()
);

create table links
(
    id         bigint primary key generated always as identity,
    user_id    bigint not null references users (id),
    title      text   not null,
    url        text   not null,
    "position" int    not null,
    created_at timestamp with time zone default now()
);

create table profiles
(
    id         bigint primary key generated always as identity,
    user_id    bigint not null references users (id),
    bio        text,
    avatar_url text,
    created_at timestamp with time zone default now()
);