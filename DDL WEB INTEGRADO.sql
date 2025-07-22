CREATE DATABASE soporte_web;

USE soporte_web;

CREATE TABLE tb_actividad (
  idActividad INT PRIMARY KEY AUTO_INCREMENT,
  idAsignacion INT NOT NULL,
  fecha DATE NOT NULL,
  detalleActividad TEXT NOT NULL,
  horasTrabajadas DOUBLE NOT NULL,
  FOREIGN KEY (idAsignacion) REFERENCES tb_asignacion(idAsignacion)
);

CREATE TABLE tb_asignacion (
  idAsignacion INT PRIMARY KEY AUTO_INCREMENT,
  idSolicitud INT NOT NULL,
  idColaborador INT NOT NULL,
  FOREIGN KEY (idSolicitud) REFERENCES tb_solicitud(idSolicitud) ON DELETE CASCADE,
  FOREIGN KEY (idColaborador) REFERENCES tbl_colaborador(idColaborador) ON DELETE CASCADE
);

CREATE TABLE tb_cliente_software (
  idCliente INT NOT NULL,
  idSoftware INT NOT NULL,
  PRIMARY KEY (idCliente, idSoftware),
  FOREIGN KEY (idCliente) REFERENCES tbl_cliente(idCliente) ON DELETE CASCADE,
  FOREIGN KEY (idSoftware) REFERENCES tbl_software(idSoftware) ON DELETE CASCADE
);

CREATE TABLE tb_solicitud (
  idSolicitud INT PRIMARY KEY AUTO_INCREMENT,
  idUsuario INT NOT NULL,
  idEstado INT NOT NULL,
  idTipoSolicitud INT NOT NULL,
  motivo TEXT NOT NULL,
  fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
  fechaCulminacion DATETIME DEFAULT NULL,
  idColaboradorQueEsCoordinador INT DEFAULT NULL,
  FOREIGN KEY (idUsuario) REFERENCES tbl_usuario(idUsuario) ON DELETE CASCADE,
  FOREIGN KEY (idEstado) REFERENCES tbl_estado_solicitud(idEstadoSolicitud) ON DELETE CASCADE,
  FOREIGN KEY (idTipoSolicitud) REFERENCES tbl_tipo_solicitud(idTipoSolicitud) ON DELETE CASCADE,
  FOREIGN KEY (idColaboradorQueEsCoordinador) REFERENCES tbl_colaborador(idColaborador)
);

CREATE TABLE tbl_cliente (
  idCliente INT PRIMARY KEY AUTO_INCREMENT,
  nombreCliente VARCHAR(100) NOT NULL,
  idTipoCliente INT NOT NULL,
  FOREIGN KEY (idTipoCliente) REFERENCES tbl_tipo_cliente(idTipoCliente) ON DELETE CASCADE
);

CREATE TABLE tbl_colaborador (
  idColaborador INT PRIMARY KEY AUTO_INCREMENT,
  idRol INT NOT NULL,
  nombreColaborador VARCHAR(100),
  telefonoColaborador VARCHAR(20),
  idCredencial INT,
  UNIQUE (idCredencial),
  FOREIGN KEY (idCredencial) REFERENCES tbl_credencial(idCredencial) ON DELETE CASCADE,
  FOREIGN KEY (idRol) REFERENCES tbl_rol(idRol) ON DELETE CASCADE
);

CREATE TABLE tbl_credencial (
  idCredencial INT PRIMARY KEY AUTO_INCREMENT,
  correo VARCHAR(100) NOT NULL,
  contraseña VARCHAR(255) NOT NULL,
  idTipoCredencial INT,
  UNIQUE (correo),
  FOREIGN KEY (idTipoCredencial) REFERENCES tbl_tipo_credencial(idTipoCredencial)
    ON DELETE SET NULL
);

CREATE TABLE tbl_estado_solicitud (
  idEstadoSolicitud INT PRIMARY KEY AUTO_INCREMENT,
  estadoSolicitud VARCHAR(50) NOT NULL,
  UNIQUE (estadoSolicitud)
);

CREATE TABLE tbl_notificacion (
  idNotificacion INT PRIMARY KEY AUTO_INCREMENT,
  mensaje TEXT NOT NULL,
  leido TINYINT(1) NOT NULL DEFAULT 0,
  fecha DATETIME NOT NULL,
  idUsuario INT NOT NULL,
  FOREIGN KEY (idUsuario) REFERENCES tbl_usuario(idUsuario)
);


CREATE TABLE tbl_rol (
  idRol INT PRIMARY KEY AUTO_INCREMENT,
  rol VARCHAR(50) NOT NULL,
  UNIQUE (rol)
);


CREATE TABLE tbl_software (
  idSoftware INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL
);


CREATE TABLE tbl_tipo_cliente (
  idTipoCliente INT PRIMARY KEY AUTO_INCREMENT,
  tipoCliente VARCHAR(50) NOT NULL,
  UNIQUE (tipoCliente)
);

CREATE TABLE tbl_tipo_credencial (
  idTipoCredencial INT PRIMARY KEY AUTO_INCREMENT,
  tipoCredencial VARCHAR(50) NOT NULL,
  UNIQUE (tipoCredencial)
);

CREATE TABLE tbl_tipo_solicitud (
  idTipoSolicitud INT PRIMARY KEY AUTO_INCREMENT,
  tipoSolicitud VARCHAR(50) NOT NULL,
  UNIQUE (tipoSolicitud)
);

CREATE TABLE tbl_usuario (
  idUsuario INT PRIMARY KEY AUTO_INCREMENT,
  idCliente INT NOT NULL,
  nombreUsuario VARCHAR(100),
  telefonoUsuario VARCHAR(20),
  idCredencial INT,
  UNIQUE (idCredencial),
  FOREIGN KEY (idCredencial) REFERENCES tbl_credencial(idCredencial) ON DELETE CASCADE,
  FOREIGN KEY (idCliente) REFERENCES tbl_cliente(idCliente) ON DELETE CASCADE
);