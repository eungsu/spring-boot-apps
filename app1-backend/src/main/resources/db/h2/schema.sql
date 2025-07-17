create table if not exists users (
    user_no int generated always as identity primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    email varchar(255) not null unique,
    nickname varchar(50),
    tel varchar(100),
    role varchar(20) default 'ROLE_USER',
    deleted boolean default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp
);

create table if not exists refresh_tokens (
    token_no int generated always as identity primary key,
    user_no int not null unique references users (user_no),
    token varchar(100) not null,
    expires timestamp
);

create table if not exists posts (
    post_no int generated always as identity primary key,
    user_no int not null references users (user_no),
    title varchar(255) not null,
    content clob not null,
    deleted boolean default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp
);