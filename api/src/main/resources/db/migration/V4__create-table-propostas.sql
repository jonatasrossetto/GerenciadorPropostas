create table propostas(

  id bigint not null auto_increment,
  id_usuario bigint not null,
  id_cliente bigint not null,
  titulo varchar(100) not null UNIQUE,
  situacao varchar(100) not null,
  data_criacao date not null,
  primary key (id),
  constraint fk_propostas_id_usuario foreign key(id_usuario) references usuarios(id),
  constraint fk_propostas_id_cliente foreign key(id_cliente) references clientes(id)

  );