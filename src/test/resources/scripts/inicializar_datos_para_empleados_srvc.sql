
INSERT INTO usuarios (email, password, account_no_expired, account_no_locked, credential_no_expired, is_enable) 
VALUES ('empleado1@hotel.com', 'hashed_password', 1, 1, 1, 1);



INSERT INTO empleados (usuario_id, apellido, dni, nombre, puesto, telefono) 
VALUES (1, 'Pérez', '12345678', 'Juan', 'Recepcionista', '3001234567');