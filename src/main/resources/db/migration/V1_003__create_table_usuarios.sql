create table USUARIOS(
	id int auto_increment primary key, 
	nome varchar(100),
	senha varchar(100),
	data_criacao timestamp not null, 
	data_alteracao timestamp not null
);