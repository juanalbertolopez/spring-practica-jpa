/* Populate tables */
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(1,'jose','Lopez','jose@gmail.com','2017-08-28','',12345,'a123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(2,'Maribel','Vasquez','mry@gmail.com','2011-08-18','',23456,'b123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(3,'ricardo','lopez','riki@gmail.com','2020-03-14','',34567,'c123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(4,'Miguel','Vasquez','mgy@gmail.com','2016-06-16','',45678,'d123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(5,'danilo','Vasquez','dany@gmail.com','2019-07-14','',56789,'e123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(6,'ramon','Vasquez','ramon@gmail.com','2011-08-18','',67890,'f123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(7,'zenaida','rondon','zena@gmail.com','2014-02-12','',78901,'g123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(8,'victor','fuentes','vito@gmail.com','2020-05-03','',89012,'h123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(9,'wiilliam','fuentes','will@gmail.com','2010-08-23','',90123,'i123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(10,'marucha','Vasquez','maru@gmail.com','2009-01-10','',01234,'j123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(11,'juan','lopez','juan@gmail.com','2018-08-19','',24682,'k123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(12,'leo','bienes','leo@gmail.com','2017-11-18','',13579,'l123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(13,'Mariangel','bienes','maryan@gmail.com','2017-05-13','',13245,'m123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(14,'cecilia','bienes','ceci@gmail.com','2018-08-28','',24367,'n123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(15,'antonio','ratio','antonio@gmail.com','2016-06-10','',46579,'y123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(16,'franklim','rueda','fran@gmail.com','2017-08-14','',57863,'o123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(17,'esteban','castillo','esteban@gmail.com','2017-07-28','',78564,'p123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(18,'dubraska','marin','duby@gmail.com','2020-03-15','',33221,'q123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(19,'Maria','Vasquez','maria@gmail.com','2019-01-18','',11223,'r123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(20,'pepe','mujica','pepe@gmail.com','2020-02-11','',22334,'s123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(21,'jorge','gutierrez','jorge@gmail.com','2019-05-13','',33445,'t123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(22,'angelica','gutierrez','angelica@gmail.com','2013-07-22','',66778,'x123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(23,'rosa','lopez','rosa@gmail.com','2013-08-19','',88778,'w123',20);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,telefono,nif,edad) VALUES(24,'yanet','castillo','yanet@gmail.com','2011-06-18','',66554,'z123',20);

/* populate tables productos */
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Panasonic Pantalla LCD',259990,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Sony Camara digital DSC-W320B',123490,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Apple ipod suffle',139990,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Sony Notebook Z110',37990,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Hewlett Packard Multifuncional F2280',69990,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990,5, NOW());
INSERT INTO productos(nombre, precio, stock, create_at) VALUES('Mica Comoda 5 Cajones', 299990,5, NOW());   

/* Creamos algunas facturas */
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('factura Bicicleta','Alguna nota importante!', 1, NOW()); 

/*creamos tabla proveedores*/
INSERT INTO proveedores(id, nombre, direccion, nif, telefono, create_at) VALUES(1,'danis','san mariano','j123','5555', NOW());


/*Creamos tabla compras proveedores*/
INSERT INTO compras(proveedor_id, recibo_compra, create_at) VALUES(1, 'A001', NOW());

INSERT INTO compras_items (cantidad,compra_id, producto_id, precio_compra) VALUES(1, 1, 1,55.2);






