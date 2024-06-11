insert into processador (modelo, velocidade, memoriaCache) values ('Intel I5 13600KF', '3.1 GHz - 4.9 GHz', '24 Mb');

insert into recurso (nome) values ('Tela Anti-reflexiva'), ('Teclado Numérico'), ('Teclado Retroiluminado'), ('Leitor Biométrico'),
('Touchscreen'), ('Touchpad Numérico'), ('Tela de Retina'), ('Teclado Mecânico'), ('Caneta Digital'), ('Tela Infinita');

insert into conexaoSemFio (nome) values ('Bluetooth'), ('Wi-Fi 6'), ('Wi-Fi Dual-Band'), ('NFC');

insert into placaVideo (modelo, memoriaVideo) values ('RTX 3050', '4 GB');

insert into armazenamento (nome, capacidade) values ('SSD', '512 GB'), ('HD', '1 TB'), ('SSD', '256 GB'), ('SSD', '128 GB'), ('SSD', '1 TB');

insert into memoriaRam (capacidade) values ('4 GB'), ('8 GB'), ('16 GB'), ('16 GB');

insert into entradaSaida (nome) values ('Ethernet (RJ-45)'), ('Áudio'), ('HDMI'), ('USB 2.0'), ('USB-C'), ('Leitor de cartão'), ('USB 3.1'), ('USB 3.0'), ('HDMI 2.1'),
('Mini HDMI'), ('Thunderbolt 4'), ('Display Port'), ('VGA');

insert into telefone (codigoArea, numero) values ('63', '99214-1871'), ('62', '3571-1224'), ('60', '4381-9342'), ('63', '6845-1987');

insert into fornecedor (nome, email, cnpj) values ('Dell Enterprises', 'dell@gmail.com', '15.736.796/0001-12'), 
('Lenovo', 'lenovo@gmail.com', '96.872.633/0001-69');

insert into telefone_fornecedor (id_fornecedor, id_telefone) values (1, 1), (2, 2);

insert into tela (tamanho, resolucao, taxaAtualizacao) values ('15,6', 'Full HD', '120 Hz'), ('15,6', 'Full HD', '120 Hz'), 
('15,6', 'Full HD', '120 Hz'), ('15,6', 'Full HD', '120 Hz');

insert into especificacao (altura, largura, profundidade, peso) values ('2.69 cm', '35.72 cm', '27.45 cm', '2.81 kg'),
('1 cm', '2 cm', '3 cm', '4 kg'), ('1 cm', '2 cm', '3 cm', '4 kg'), ('1 cm', '2 cm', '3 cm', '4 kg');

insert into notebook (descricao, linha, serie, preco, modelo, sistema_operacional, gamer, num_usb, limite_ram, estoque, id_fornecedor,
id_placa_video, id_processador, id_tela, id_especificacao, tipo_placa_video) values ('Dell G15', 'G Series', 'G15 5530', 5497,
'g5530w002bts', 'Windows 11', true, 4, '10 GB', 10, 1, 1, 1, 1, 1, 2);

insert into notebook_recurso (id_notebook, id_recurso) values (1, 1), (1, 2), (1, 3);

insert into notebook_armazenamento (id_notebook, id_armazenamento) values (1, 1), (1, 2);

insert into notebook_memoria_ram (id_notebook, id_memoria_ram) values (1, 1), (1, 2);

insert into notebook_conexao (id_notebook, id_conexao) values (1, 1);

insert into notebook_entrada_saida (id_notebook, id_entrada_saida) values (1, 1);

insert into estado (nome, sigla) values ('Goias', 'GO'), ('Tocantins', 'TO');

insert into cidade (nome, id_estado) values ('Anapolis', 1), ('Palmas', 2);

insert into usuario (email, senha) values ('rafael@gmail.com', '+S1oFRn+kGusilJo843gPnKyJTINwGmWF+sk6c/Cmda3lGZPIkDR8Qy1Z3k/W9+Wa09NbNzQojxR2AaOGL0/dA=='),
('maria@gmail.com', 'k4IJ09tBrs3/1bNrVS4tERIXsTWdK/EFWZEFwA3ahZLzx7vzpQHwOOC6uFY7jH4mni/tMNh2YvX1XypBOyj4lA==');

insert into pessoa (nome, data_nascimento, cpf, sexo, id_usuario) values 
('Rafael', '2000-03-22', '955.514.170-34', 1, 1),
('Maria', '1980-08-05', '581.019.600-40', 2, 2);

insert into telefone_pessoa (id_pessoa, id_telefone) values (1, 3), (2, 4);

insert into cliente (aceitaMarketing, id_pessoa) values (true, 1);

insert into funcionario (salario, dataContrato, id_pessoa, perfil) values (5300, '2024-04-24', 2, 1);

insert into cupom (codigo, percentual_desconto, data_validade, id_fornecedor) values ('DELL15OFF', 0.15, '2024-06-10 23:59:59.000000', 1),
('LENOVO20OFF', 0.20, '2024-06-10 23:59:59.000000', 2);

insert into pedido (data, total, id_cliente) values ('2024-05-22 17:03:55.030719', 3500, 1);

insert into itempedido (preco, id_pedido, quantidade, id_notebook, id_cupom) values 
(3500, 1, 1, 1, 1);

insert into statuspedido (id_pedido, status) values (1, 2), (1, 3);