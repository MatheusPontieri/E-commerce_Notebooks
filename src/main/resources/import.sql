-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
INSERT INTO processador (modelo, geracao, velocidade, nucleos, threads, memoriaCache) VALUES ('i5-14600KF', '14° Geração', '2.6 GHz - 5.3 GHz', 14, 20, '24 MB');

INSERT INTO placaVideo (modelo, memoriaVideo) VALUES ('RTX 3050', '8 GB');

INSERT INTO memoriaRam (capacidade, expansivelAte) VALUES ('8 GB', '16 GB');

INSERT INTO notebook (descricao, modelo, preco, isGamer, id_processador, id_placa_video, id_memoria_ram) 
VALUES ('Notebook Gamer Dell G15 5530 Intel Core i5 13450HX 13ª Geração GeForce RTX 3050 15,6" 8GB SSD 512GB Windows 11 g5530w002bts', 'g5530w002bts',
5049.59, true, 1, 1, 1);