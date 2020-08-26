CREATE DATABASE Intelaf;
USE Intelaf;
-- VARCHAR()
-- INT
-- NOT NULL
--
--
CREATE TABLE Tienda(
    nombre VARCHAR(30) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    codigo VARCHAR (6) NOT NULL,
    telefono1 VARCHAR(9) NOT NULL,
    telefono2 VARCHAR(9),
    email VARCHAR(45),
    horario VARCHAR(10),
    PRIMARY KEY (codigo)
);

CREATE TABLE Tiempo(  -- CREAR METODO
    codigo INT AUTO_INCREMENT NOT NULL,
    tiempo INT NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE Empleado(
    codigo VARCHAR(10) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    DPI VARCHAR(15) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    email VARCHAR(45),
    NIT VARCHAR(10),
    PRIMARY KEY (codigo)
);
		
CREATE TABLE Cliente(
    NIT VARCHAR(10) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    direccion VARCHAR(45) ,
    email VARCHAR(45) ,
    DPI VARCHAR(15),
    CREDITO DOUBLE,
    PRIMARY KEY (NIT)
);

CREATE TABLE Producto( -- CREAR METODO
    codigo VARCHAR(8) NOT NULL,
    nombre VARCHAR(45)NOT NULL,
    fabricante VARCHAR(30) NOT NULL,
    precio DOUBLE NOT NULL,
    descripcion VARCHAR(100),
    garantia INT,
    PRIMARY KEY (codigo)
);

CREATE TABLE info_compra(  -- CREAR METODO
    codigo INT AUTO_INCREMENT NOT NULL,
    anticipo DOUBLE,
    total DOUBLE,
    PRIMARY KEY (codigo)
);

CREATE TABLE Pedido(  -- CREAR METODO
    codigo INT AUTO_INCREMENT NOT NULL,
    fecha DATE,
    recibido TINYINT(1),
    PRIMARY KEY (codigo)
);
CREATE TABLE Pedido_antiguo(  -- CREAR METODO
    codigo INT NOT NULL,
    fecha DATE,
    recibido TINYINT(1),
    PRIMARY KEY (codigo)
);

CREATE TABLE Venta(  -- CREAR METODO
    codigo INT AUTO_INCREMENT NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE Tienda_tiene_Producto(  -- CREAR METODO
    codigo_tienda VARCHAR(6) NOT NULL,
    codigo_producto VARCHAR(8) NOT NULL,
    cantidad INT,
    FOREIGN KEY (codigo_tienda) REFERENCES Tienda(codigo),
    FOREIGN KEY (codigo_producto) REFERENCES Producto(codigo)
);

ALTER TABLE Tiempo ADD (
    tienda_emisora VARCHAR(6) NOT NULL,
    tienda_receptora VARCHAR(6) NOT NULL,
    FOREIGN KEY (tienda_emisora) REFERENCES Tienda(codigo),
    FOREIGN KEY (tienda_receptora) REFERENCES Tienda(codigo));

CREATE TABLE Sesion(
    codigo_tienda VARCHAR(6) NOT NULL,
    cliente_NIT VARCHAR(10) NOT NULL,
    FOREIGN KEY (codigo_tienda) REFERENCES Tienda(codigo),
    FOREIGN KEY (cliente_NIT) REFERENCES Cliente(NIT)
);

CREATE TABLE info_compra_producto(
    codigo INT AUTO_INCREMENT NOT NULL,
    codigo_producto VARCHAR(8) NOT NULL,
    cantidad INT NOT NULL,
    codigo_compra INT NOT NULL,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_producto) REFERENCES Producto (codigo),
    FOREIGN KEY (codigo_compra) REFERENCES info_compra(codigo)
);

ALTER TABLE info_compra ADD(
    codigo_tiempo INT NOT NULL,
    NIT_cliente VARCHAR(10) NOT NULL,
    FOREIGN KEY (codigo_tiempo) REFERENCES Tiempo(codigo),
    FOREIGN KEY (NIT_cliente) REFERENCES Cliente(NIT));
    
ALTER TABLE Pedido ADD(
    codigo_compra INT NOT NULL,
    FOREIGN KEY (codigo_compra) REFERENCES info_compra(codigo)
    );

ALTER TABLE Pedido_antiguo ADD(
    codigo_compra INT NOT NULL,
    FOREIGN KEY (codigo_compra) REFERENCES info_compra(codigo)
);

ALTER TABLE Venta ADD(
    codigo_compra INT NOT NULL,
    FOREIGN KEY (codigo_compra) REFERENCES info_compra(codigo));
