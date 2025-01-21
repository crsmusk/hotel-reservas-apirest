-- clientes
INSERT INTO clientes (id, apellido, dni, nombre, telefono) VALUES
(1, 'Gómez', '12345678', 'Juan', '3001234567'),
(2, 'Pérez', '87654321', 'María', '3017654321'),
(3, 'Juan', 'Pérez', '123456789', '5551234567');

--habitaciones
INSERT INTO habitaciones (id, capacidad, estado, precio_noche, tamano, accesibilidad, descripcion, preferencia, tipo_habitacion) VALUES
(1, 2, 0, 100.00, 25, 'Acceso para discapacitados', 'Habitación doble con baño privado', 'Cerca de piscina', 'Doble'),
(2, 4, 0, 200.00, 40, 'Acceso estándar', 'Suite familiar con balcón', 'Vista al mar', 'Suite'),
(3, 3, 1, 150.00, 30, 'Acceso estándar', 'Habitación triple con vista al jardín', 'Cerca del restaurante', 'Triple');

--reservaciones
INSERT INTO reservas (id, fecha_entrada, fecha_salida, cliente_id) VALUES
(1, '2025-01-20', '2025-01-25', 1),
(2, '2025-02-01', '2025-02-05', 2);

INSERT INTO reserva_habitacion (habitacion_id, reserva_id) VALUES
(1, 1),
(2, 2);