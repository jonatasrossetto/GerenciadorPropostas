create table itens_da_proposta(

  id bigint not null auto_increment,
  id_proposta bigint not null,
  id_produto bigint not null,
  quantidade bigint not null,
  valor_unitario decimal(10,2) not null,
  prazo_entrega bigint not null,
  data_criacao date not null,
  primary key (id),
  constraint fk_itensdaproposta_id_proposta foreign key(id_proposta) references propostas(id),
  constraint fk_itensdaproposta_id_produto foreign key(id_produto) references produtos(id)
  
  );