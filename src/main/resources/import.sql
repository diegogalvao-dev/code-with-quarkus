-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;



insert into corda (calibre, id_guitarra) values('Medium', 1);
insert into corda (calibre, id_guitarra) values('Heavy', 3);
insert into corda (calibre, id_guitarra) values('SuperLight', 2);

insert into estojo (material, estiloCase) values('tecido', 1);
insert into estojo (material, estiloCase) values('madeira', 2);
insert into estojo (material, estiloCase) values('tecido', 3);

insert into configuracaoAudio (tipoAmplificador, presetEqualizador, temPedaleira) values('a', 'r', true);
insert into configuracaoAudio (tipoAmplificador, presetEqualizador, temPedaleira) values('b', 'g', false);
insert into configuracaoAudio (tipoAmplificador, presetEqualizador, temPedaleira) values('v', 'h', true);





