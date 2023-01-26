INSERT INTO customer (name, surname, dni, phone, address, active) VALUES ('Rosario','Perez','30.854.943','+5493795028536','Avenida Roca 1234', 1);
INSERT INTO customer (name, surname, dni, phone, address, active) VALUES ('Juan','Rodriguez','25.158.247','+5493794002617','Avellaneda 5014', 1);

INSERT INTO provider (name, cuit, phone, address, active) VALUES ('Julio SA','30-125635456622548-3','+54937954435689','Avenida Farias 123', 1);
INSERT INTO provider (name, cuit, phone, address, active) VALUES ('Diarco SA','30-205856592516531-5','+54937454584263','Avenida Corrientes 5624', 1);


INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Gaseosa Fanta 3L','Gaseosa botella de 3 litros sabor naranja', 600.00, 20, 8, 1, 1);
INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Chocolatada Nesquit','Polvo para chocolatada 500 grs', 300.00, 50, 15, 1, 1);
INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Leche Sancor','Leche en caja de 1 litro', 200.00, 30, 10, 1, 1);
INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Cafe Cabrales 1 kg','Cafe molido en grano paquete familiar de 1 kg', 2000.00, 100, 15, 2, 1);
INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Arroz Luchetti','Arroz 000 paquete de 1 kg', 150.00, 80, 10, 2, 1);
INSERT INTO product (name, description, price, stock, stock_minimum, provider_id, active) VALUES ('Fideo Dentel','Fideo tallarin paquete de 1 kg', 100.00, 200, 12, 2, 1);

INSERT INTO purchase (total_price, provider_id) VALUES (80000.00, 1);
INSERT INTO purchase (total_price, provider_id) VALUES (50000.00, 2);

INSERT INTO sale (total_price, customer_id) VALUES (8000.00, 1 );
INSERT INTO sale (total_price, customer_id) VALUES (4600.00, 2);

INSERT INTO purchase_detail (purchase_id, quantity, product_id) VALUES (1, 10, 1) , (1, 15, 2) , (1, 20, 3);
INSERT INTO purchase_detail (purchase_id, quantity, product_id) VALUES (2, 20, 4) , (2, 13, 5) , (2, 11, 6);

INSERT INTO sale_detail (sale_id, quantity, product_id) VALUES (1, 5, 2) , (1, 2, 1) , (1, 10, 5) , (1, 20, 6);
INSERT INTO sale_detail (sale_id, quantity, product_id) VALUES (2, 10, 3) , (2, 1, 4) , (2, 2, 1);