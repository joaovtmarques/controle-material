INSERT INTO users (name, war_Name, rank, company, cpf, telephone, email, password, type) VALUES ('João Vitor da Silva', 'Vitor Silva', 'Sd EP', 'CCAp', '00000000000', '12999999999', 'admin@email.com', '$2a$12$ywbYDb9b4TgM5Lt/t3BZa.VwOVnk4xwaFtpQF/oHKtV3LRU31K5ta', 'INFORMATICA');

INSERT INTO roles (role) VALUES ('ADMIN');
INSERT INTO roles (role) VALUES ('ADMIN_PELCOM');

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 2);

INSERT INTO categories (id, name, type) VALUES (1, 'CATEGORIA TESTE', 'INFORMATICA');

INSERT INTO equipments (id, name, is_in_charge, amount, serial_number, total_price, condition, category_id, observation, type, state, amount_out) VALUES (1, 'EQUIPAMENTO TESTE1', true, 10, '123456789', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 'DISPONIVEL', 0);
INSERT INTO equipments (id, name, is_in_charge, amount, serial_number, total_price, condition, category_id, observation, type, state, amount_out) VALUES (2, 'EQUIPAMENTO TESTE2', true, 10, '1234567890', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 'DISPONIVEL', 0);
INSERT INTO equipments (id, name, is_in_charge, amount, serial_number, total_price, condition, category_id, observation, type, state, amount_out) VALUES (3, 'EQUIPAMENTO TESTE3', true, 10, '1234567891', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 'DISPONIVEL', 0);

INSERT INTO items (id, name, is_in_charge, amount, serial_number, price, condition, category_id, observation, type, amount_out) VALUES (1, 'EQUIPAMENTO TESTE1', true, 10, '123456789', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 0);
INSERT INTO items (id, name, is_in_charge, amount, serial_number, price, condition, category_id, observation, type, amount_out) VALUES (2, 'EQUIPAMENTO TESTE2', true, 10, '1234567890', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 0);
INSERT INTO items (id, name, is_in_charge, amount, serial_number, price, condition, category_id, observation, type, amount_out) VALUES (3, 'EQUIPAMENTO TESTE3', true, 10, '1234567891', '1000.00', 'BOM', 1, 'OBSERVACAO', 'INFORMATICA', 0);