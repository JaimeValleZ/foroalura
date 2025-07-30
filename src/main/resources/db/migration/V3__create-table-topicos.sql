create table topicos
(
    id       bigint       not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje    varchar(255) not null unique,
    fecha_creacion datetime not null,
    estado varchar(100) not null,
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key (id),
    constraint fk_usuario_id foreign key (usuario_id) references usuarios(id),
    constraint fk_curso_id foreign key (curso_id) references cursos(id)
);