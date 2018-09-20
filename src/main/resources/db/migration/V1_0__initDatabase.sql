CREATE TABLE tb_count_security
(
  id serial,
  valor integer,
  CONSTRAINT tb_count_security_pk PRIMARY KEY (id)
);


CREATE TABLE tb_count_insecurity_parallel
(
  id serial,
  valor integer,
  CONSTRAINT tb_count_insecurity_parallel_pk PRIMARY KEY (id)
);

CREATE TABLE tb_count_insecurity_notparallel
(
  id serial,
  valor integer,
  CONSTRAINT tb_count_insecurity_notparallel_pk PRIMARY KEY (id)
);

insert into tb_count_security(valor) values(0);
insert into tb_count_insecurity_parallel(valor) values(0);
insert into tb_count_insecurity_notparallel(valor) values(0);