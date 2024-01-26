# insert into usuarios(id, username, password, email) VALUES (1,'admin','admin','admin@admin.com');
insert into relaciones_curso.clientes(id, apellido, actualizado_en, creado_en, forma_pago, nombre) VALUES (1,'admin',now(),now(),'efectivo','admin');
insert into direcciones(calle, numero) values('vaticano',456);
insert into direcciones(calle, numero) values('colon',123);
insert into tbl_clientes_direcciones(id_cliente, id_direccion) values (1,1);
insert into tbl_clientes_direcciones(id_cliente, id_direccion) values (1,2);
