
INSERT INTO roles (id, nombre_rol)
VALUES (1, 'Admin');

INSERT INTO roles (id, nombre_rol)
VALUES (2, 'Editor');

INSERT INTO roles (id, nombre_rol)
VALUES (3, 'Viewer');
-------------------------------------------------------------------------

INSERT INTO usuarios (id, email, password, account_no_expired, account_no_locked, credential_no_expired, is_enable)
VALUES (1, 'usuario1@ejemplo.com', 'password123', 1, 1, 1, 1);

INSERT INTO usuarios (id, email, password, account_no_expired, account_no_locked, credential_no_expired, is_enable)
VALUES (2, 'usuario2@ejemplo.com', 'password456', 1, 1, 1, 1);

INSERT INTO usuarios (id, email, password, account_no_expired, account_no_locked, credential_no_expired, is_enable)
VALUES (3, 'usuario3@ejemplo.com', 'password789', 1, 1, 1, 1);

--------------------------------------------------------------------------
INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (1, 1); 
INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (1, 2); 

INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (2, 2); 
INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (2, 3); 

INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (3, 1); 
INSERT INTO usuario_rol (usuario_id, rol_id)
VALUES (3, 3); 
