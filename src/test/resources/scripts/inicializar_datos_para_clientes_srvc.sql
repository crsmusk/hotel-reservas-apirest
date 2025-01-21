

INSERT INTO usuarios (email, password, is_enable, account_no_expired, account_no_locked, credential_no_expired)
VALUES ('cliente@example.com', 'password_encriptado', 1, 1, 1, 1);



INSERT INTO clientes (usuario_id, nombre, apellido, dni, telefono)
VALUES (1, 'Juan', 'Pérez', '123456789', '5551234567');