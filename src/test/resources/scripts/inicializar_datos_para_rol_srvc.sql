-- roles
INSERT INTO roles (id, nombre_rol)
VALUES (1, 'Admin');

INSERT INTO roles (id, nombre_rol)
VALUES (2, 'Editor');

INSERT INTO roles (id, nombre_rol)
VALUES (3, 'Viewer');

--  permisos
INSERT INTO permisos (id, nombre_permiso)
VALUES (1, 'CREAR_USUARIO');

INSERT INTO permisos (id, nombre_permiso)
VALUES (2, 'EDITAR_USUARIO');

INSERT INTO permisos (id, nombre_permiso)
VALUES (3, 'VER_RESERVAS');

INSERT INTO permisos (id, nombre_permiso)
VALUES (4, 'EDITAR_RESERVAS');

INSERT INTO permisos (id, nombre_permiso)
VALUES (5, 'VER_REPORTES');

INSERT INTO permisos (id, nombre_permiso)
VALUES (6, 'EXPORTAR_REPORTES');

-- rol 'Admin'
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (1, 1); -- CREAR_USUARIO
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (1, 2); -- EDITAR_USUARIO

-- rol 'Editor'
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (2, 3); -- VER_RESERVAS
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (2, 4); -- EDITAR_RESERVAS

--  rol 'Viewer'
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (3, 5); -- VER_REPORTES
INSERT INTO rol_permiso (rol_id, permiso_id)
VALUES (3, 6); -- EXPORTAR_REPORTES
