INSERT INTO ROLES (id, role_name) values (1, 'ADMIN');
INSERT INTO ROLES (id, role_name) values (2, 'USER');

INSERT INTO USERS(id, username, password) values (1, 'cscs', '$2y$10$5NEVsBG1XG8DxohjH7Jkb.lidiIBHKizYXqB1krQLR2WpM0WPgI1O'); --user
INSERT INTO USERS(id, username, password) values (2, 'admin', '$2y$12$DUVTvVtsr/.A6XQDM5tLE.jWbt.WZhp2ubVv2lBRqVlOqnqdJ9MKC'); --admin

INSERT INTO USER_ROLES (user_id, role_id) values (1,2);
INSERT INTO USER_ROLES (user_id, role_id) values (2,1);