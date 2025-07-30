create table usuarios
(
    id bigint not null auto_increment,
    username varchar(100) not null unique,
    password varchar(255) not null,
    role varchar(100) not null,
    enabled tinyint not null,

    primary key (id)
);