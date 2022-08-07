create table USUARIOS_PERFIS (
   "USUARIO_ID" BIGINT NOT NULL,
   "PERFIS_ID" BIGINT NOT NULL
); 

alter table USUARIOS_PERFIS
add foreign key ("USUARIO_ID") references USUARIOS("ID");

alter table USUARIOS_PERFIS
add foreign key ("PERFIS_ID") references PERFIL("ID");
