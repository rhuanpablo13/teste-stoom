insert into brand(id, brand, active) values (nextval('brand_seq'), 'heineken', false); -- 1
insert into brand(id, brand, active) values (nextval('brand_seq'), 'coca-cola', true); -- 2
insert into brand(id, brand, active) values (nextval('brand_seq'), 'colgate', true); -- 3
insert into brand(id, brand, active) values (nextval('brand_seq'), 'sorriso', false); -- 4
insert into brand(id, brand, active) values (nextval('brand_seq'), 'tang', true); -- 5
insert into brand(id, brand, active) values (nextval('brand_seq'), 'nissin', true); -- 6
insert into brand(id, brand, active) values (nextval('brand_seq'), 'omo', true); -- 7
insert into brand(id, brand, active) values (nextval('brand_seq'), 'minuano', true); -- 8
insert into brand(id, brand, active) values (nextval('brand_seq'), 'negresco', true); -- 9
insert into brand(id, brand, active) values (nextval('brand_seq'), 'mabel', false); -- 10
insert into brand(id, brand, active) values (nextval('brand_seq'), 'nestle', true); -- 11
insert into brand(id, brand, active) values (nextval('brand_seq'), 'sadia', true); -- 12
insert into brand(id, brand, active) values (nextval('brand_seq'), 'perdigao', true); -- 13

insert into category(id, category, active) values (nextval('category_seq'), 'bebidas', true); -- 1
insert into category(id, category, active) values (nextval('category_seq'), 'limpeza', true); -- 2
insert into category(id, category, active) values (nextval('category_seq'), 'higiene-pessoal', false); -- 3
insert into category(id, category, active) values (nextval('category_seq'), 'biscoitos', true); -- 4
insert into category(id, category, active) values (nextval('category_seq'), 'frios', true); -- 5
insert into category(id, category, active) values (nextval('category_seq'), 'massas', true); -- 6

insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'cerveja heinekein pilsen', 12.99, 'SKU_1', 1, 1, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'cerveja heinekein edição especial', 15.99, 'SKU_2', 1, 1, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'coca cola 1,5', 7.99, 'SKU_3', 2, 1, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'coca cola 2,00', 9.99, 'SKU_4', 2, 1, true);

insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'pasta de dente', 3.99, 'SKU_5', 3, 3, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'pasta de dente', 1.99, 'SKU_6', 4, 3, true);

insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'suco em pó', 0.99, 'SKU_7', 5, 1, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'miojo sabor frango', 0.99, 'SKU_8', 6, 6, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'miojo sabor carne', 0.99, 'SKU_9', 6, 6, true);

insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'sabão em pó', 0.99, 'SKU_10', 7, 2, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'sabão em pó', 0.99, 'SKU_11', 8, 2, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'detergente', 2.99, 'SKU_12', 8, 2, true);

insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'biscoito negresco', 3.99, 'SKU_13', 9, 4, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'biscoito mabel', 5.99, 'SKU_14', 10, 4, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'nescau', 8.99, 'SKU_15', 11, 4, true);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'peito de frango congelado', 12.99, 'SKU_16', 12, 5, false);
insert into product (id, name, price, sku, brand, category, active) values (nextval('product_seq'), 'peito de frango congelado', 10.99, 'SKU_17', 13, 5, false);


INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 9.99, 'SKU_1');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-03-10 00:00:00', 10.99, 'SKU_1');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-06-05 00:00:00', 12.99, 'SKU_1');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 10.99, 'SKU_2');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-02-10 00:00:00', 12.99, 'SKU_2');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-05-08 00:00:00', 15.99, 'SKU_2');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 6.99, 'SKU_3');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-03-21 00:00:00', 7.99, 'SKU_3');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 8.99, 'SKU_4');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-08-12 00:00:00', 9.99, 'SKU_4');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 2.99, 'SKU_5');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-02-24 00:00:00', 3.99, 'SKU_5');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 0.99, 'SKU_6');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-02-23 00:00:00', 1.99, 'SKU_6');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 0.79, 'SKU_7');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-03-22 00:00:00', 0.89, 'SKU_7');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-03-22 00:00:00', 0.99, 'SKU_7');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:000', 0.99, 'SKU_8');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-03-22 00:00:00', 0.99, 'SKU_8');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 0.79, 'SKU_9');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-04-12 00:00:00', 0.89, 'SKU_9');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-04-12 00:00:00', 0.99, 'SKU_9');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 0.89, 'SKU_10');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-05-14 00:00:00', 0.99, 'SKU_10');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 0.99, 'SKU_11');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 2.99, 'SKU_12');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 3.99, 'SKU_13');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 5.99, 'SKU_14');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 6.99, 'SKU_15');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-10-01 00:00:00', 8.99, 'SKU_15');

INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:000', 12.99, 'SKU_16');
INSERT INTO public.historic_price (id, "date", price, sku) VALUES(nextval('historic_price_seq'), '2023-01-01 00:00:00', 10.99, 'SKU_17');
