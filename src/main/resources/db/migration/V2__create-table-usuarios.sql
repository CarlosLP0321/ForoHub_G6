
create table usuarios(

    id bigint not null auto_increment,
    login varchar(255) not null,
    clave varchar(300) not null,

    primary key(id)
);
