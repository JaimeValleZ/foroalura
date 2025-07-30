create table respuestas
(
    id       bigint       not null auto_increment,
    mensaje    varchar(255) not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    solucion tinyint not null,
    usuario_id bigint not null,

    primary key (id),
    constraint fk_respuesta_usuario__id foreign key (usuario_id) references usuarios(id),
    constraint fk_topico_id foreign key (topico_id) references topicos(id)
);