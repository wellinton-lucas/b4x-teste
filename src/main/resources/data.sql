INSERT INTO produto (id, public_id, nome, descricao, preco, data_criacao, data_ultima_modificacao, categoria) 
VALUES (31, '019c1c0e-4b91-7636-b74f-1c396e5ef8a3', 'Produto1', 'Descrição do produto 1', 10.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TESTE1');

INSERT INTO produto (id, public_id, nome, descricao, preco, data_criacao, data_ultima_modificacao, categoria) 
VALUES (32, '029c1c0e-4b91-7636-b74f-1c396e5ef8a4', 'Produto2', 'Descrição do produto 2', 25.50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TESTE1');

INSERT INTO produto (id, public_id, nome, descricao, preco, data_criacao, data_ultima_modificacao, categoria) 
VALUES (33, '039c1c0e-4b91-7636-b74f-1c396e5ef8a5', 'Produto3', 'Descrição do produto 3', 99.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TESTE2');

INSERT INTO produto (id, public_id, nome, descricao, preco, data_criacao, data_ultima_modificacao, categoria) 
VALUES (34, '049c1c0e-4b91-7636-b74f-1c396e5ef8a6', 'Produto4', 'Descrição do produto 4', 15.75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TESTE2');

INSERT INTO produto (id, public_id, nome, descricao, preco, data_criacao, data_ultima_modificacao, categoria) 
VALUES (35, '059c1c0e-4b91-7636-b74f-1c396e5ef8a7', 'Produto5', 'Descrição do produto 5', 199.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TESTE1');

INSERT INTO usuario (id, public_id, login, senha, roles) 
VALUES (12, '069c1c0e-4b91-7636-b74f-1c396e5ef8a8', 'user@gmail.com', '$2a$10$fI6VH9Bc1bgWEEJNsuLF0O7hvrlHCpYRtsO9aFgVnqo/9aYsugDoe', 'CLIENTE');

INSERT INTO usuario (id, public_id, login, senha, roles) 
VALUES (13, '079c1c0e-4b91-7636-b74f-1c396e5ef8a9', 'admin@gmail.com', '$2a$10$fI6VH9Bc1bgWEEJNsuLF0O7hvrlHCpYRtsO9aFgVnqo/9aYsugDoe', 'ADMIN');
