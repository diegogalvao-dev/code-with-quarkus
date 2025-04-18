-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into guitarra (nome, tipo, modelos) values ('Waldman', 'Eletrica', 1);
insert into guitarra (nome, tipo, modelos) values ('Fender', 'Acustica', 4);
insert into guitarra (nome, tipo, modelos) values ('Dreadnought', 'Acustica', 3);
insert into guitarra (nome, tipo, modelos) values ('Strinberg', 'Eletrica', 5);

insert into corda (calibre, id_guitarra) values('Medium', 1);
insert into corda (calibre, id_guitarra) values('Heavy', 3);
insert into corda (calibre, id_guitarra) values('SuperLight', 2);

insert into estojo (material, estiloCase) values('tecido', 1);
insert into estojo (material, estiloCase) values('madeira', 2);
insert into estojo (material, estiloCase) values('tecido', 3);

insert into catalogoDeArtistas (nome, id_guitarra, estiloMusical) values('Jimi Hendrix', 1, 1);
