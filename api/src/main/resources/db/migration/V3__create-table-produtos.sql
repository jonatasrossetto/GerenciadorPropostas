create table produtos(

  id bigint not null auto_increment,
  idUsuario bigint not null,
  descricao varchar(100) not null UNIQUE,
  unidade varchar(100) not null,
  valorUnitarioTabela decimal(10,2) not null,
  primary key(id)

  );