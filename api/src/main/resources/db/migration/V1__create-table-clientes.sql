create table clientes(

  id bigint not null auto_increment,
  nome varchar(100) not null,
  email varchar(100) not null UNIQUE,
  telefone varchar(100) not null,
  empresa varchar(100) not null,

  primary key(id)

  );