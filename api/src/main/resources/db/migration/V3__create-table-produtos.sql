create table produtos(

  id bigint not null auto_increment,
  id_usuario bigint not null,
  descricao varchar(100) not null UNIQUE,
  unidade varchar(100) not null,
  valor_unitario_tabela decimal(10,2) not null,
  primary key(id)

  );