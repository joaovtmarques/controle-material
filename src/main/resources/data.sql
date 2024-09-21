INSERT INTO users (name, war_Name, rank, company, cpf, telephone, email, password) VALUES ('João Vitor da Silva', 'Vitor Silva', 'Sd EP', 'CCAp', '00000000000', '12999999999', 'admin@email.com', '$2a$12$ywbYDb9b4TgM5Lt/t3BZa.VwOVnk4xwaFtpQF/oHKtV3LRU31K5ta');

INSERT INTO roles (role) VALUES ('ADMIN');
INSERT INTO roles (role) VALUES ('ADMIN_PELCOM');

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 2);