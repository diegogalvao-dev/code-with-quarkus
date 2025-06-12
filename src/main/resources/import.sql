

insert into pessoa (name) values ('gerente antonio');
insert into pessoa (name) values ('cliente da silva');

insert into pessoafisica (id, cpf) values (1, '11111111111');
insert into pessoafisica (id, cpf) values (2, '22222222222');
--
insert into usuario(username, senha, perfil, idpessoafisica)
    values (
        'gerente',
        'p0eulcN/vk7A1gBuaJYP6w7JSlPPZuf3LD7UJOMDicepk2568OrgzS1WUz0Z/YP9Yq2Uh8v695ewG4JTZXMrKA==',
        1,
        1);

insert into usuario(username, senha, perfil, idpessoafisica)
    values (
        'cliente',
        'p0eulcN/vk7A1gBuaJYP6w7JSlPPZuf3LD7UJOMDicepk2568OrgzS1WUz0Z/YP9Yq2Uh8v695ewG4JTZXMrKA==',
        2,
        2);