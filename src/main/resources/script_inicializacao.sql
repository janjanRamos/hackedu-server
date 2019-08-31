INSERT INTO evolua.cargo
(ativo, nome)
VALUES(1, 'Cargo Teste');

INSERT INTO evolua.setor
(ativo, nome)
VALUES(1, 'Setor Teste');

INSERT INTO evolua.pessoa
(ativo, cpf, email, gestor, nome, id_cargo, id_setor)
VALUES(1, '111.111.111-11', 'teste@email.com', 0, 'Teresa Fernandes', 1, 1);