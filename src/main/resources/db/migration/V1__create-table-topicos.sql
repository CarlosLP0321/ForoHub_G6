
create table topicos(

    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje varchar(255) not null,
    fechaDeCreacion DATETIME not null,
    estado varchar(50) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)

);
