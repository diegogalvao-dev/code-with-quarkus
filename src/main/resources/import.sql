insert into pessoa (name) values ('gerente antonio');
insert into pessoa (name) values ('cliente da silva');
insert into pessoa (name) values ('fulano');

insert into pessoafisica (id, cpf) values (1, '11111111111');
insert into pessoafisica (id, cpf) values (2, '22222222222');
insert into pessoafisica (id, cpf) values (3, '33333333333');
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

INSERT INTO Guitarra (nome, modelos, estoque, price, TIPO_GUITARRA, numero_captadores, tipoPonte)
VALUES ('Fender Stratocaster Sunburst', 'STRATOCASTER', 10, 1250.99, 'ELETRICA', 3, 'Tremolo Sincronizado');

INSERT INTO Guitarra (nome, modelos, estoque, price, TIPO_GUITARRA, numero_captadores, tipoPonte)
VALUES ('Taylor GS Mini Mahogany', 'SG', 15, 699.00, 'ACUSTICA', NULL, NULL);

INSERT INTO Endereco (id_usuario, quadra, lote) VALUES (1, '603note', '89');
INSERT INTO Endereco (id_usuario, quadra, lote) VALUES (2, '403sul', '07');
INSERT INTO Endereco (id_usuario, quadra, lote) VALUES (2, '830norte', '72');

INSERT INTO Pedido (dataHora, id_usuario, totalPedido, id_endereco, status)
VALUES ('2024-05-21 14:30:00', 2, 1600.98, 2, 'AGUARDANDO_PAGAMENTO');

INSERT INTO ItemPedido (id_pedido, id_guitarra, quantidade, preco)
VALUES (1, 1, 1, 1250.99);

