create table movies_tb (
  id serial primary key not null,
  titulo text not null unique,
  resumo text not null
);