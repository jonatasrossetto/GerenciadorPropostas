create table observacoes_da_proposta(

  id bigint not null auto_increment,
  id_proposta bigint not null,
  observacao varchar(255) not null,
  data_criacao date not null,
  primary key (id),
  constraint fk_observacoes_id_proposta foreign key(id_proposta) references propostas(id)

  );