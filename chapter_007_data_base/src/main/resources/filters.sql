DROP SCHEMA IF EXISTS filters CASCADE;
CREATE SCHEMA filters;

-- Первая существующая схема в пути поиска
-- считается схемой по умолчанию для новых объектов.
SET search_path TO filters, public;
-- Теперь мы можем обращаться к таблице без указания схемы

-- установим российские денежки
-- ==(так и не получилось увидеть в таблице символ валюты вместо "?")==
-- SET lc_monetary TO 'C';
SET lc_monetary TO "Russian_Russia.1251";
-- SET lc_monetary TO "en_US.UTF-8";
-- SET lc_monetary TO "English_US.1251";

-- создать таблицы:
--       product(id, name, type_id, expired_date, price)
--       type(id, name)

CREATE TABLE type (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE product (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50),
    type_id      SMALLINT REFERENCES type (id), -- -32768 .. +32767
    expired_date DATE,                          -- срок годности 1999-01-08
    price        MONEY                          --  '$1,000.00'
);

-- наполним таблицы

INSERT
    INTO type (name)
    VALUES ('молочная продукция'),
           ('консервы'),
           ('хлебобулочные'),
           ('крупы');

INSERT
    INTO product (name, type_id, expired_date, price)
    VALUES ('кефир', 1, '2019-10-18', '65,50'),
           ('молоко', 1, '2019-10-05', '51,00'),
           ('творог', 1, '2019-10-07', '78,13'),
           ('сливочное мороженое', 1, '2019-11-07', '78,13'),
           ('клубничное мороженое', 1, '2019-12-07', '78,13'),
           ('сайра', 2, '2020-02-01', '78'),
           ('тушёнка', 2, '2020-05-10', '105'),
           ('горошек', 2, '2021-10-20', '67'),
           ('булка хлеба', 3, '2019-10-03', '43'),
           ('батон нарезной', 3, '2019-10-03', '28'),
           ('рогалик', 3, '2019-11-05', '35'),
           ('гречневая', 4, '2010-09-23', '78'),
           ('рисовая', 4, '2010-10-17', '69'),
           ('овсяная', 4, '2010-05-20', '21');

-- 1. Написать запрос получение всех продуктов с типом "СЫР" (тип взял другой)
SELECT p.name
    FROM product       p
             JOIN type t ON p.type_id = t.id
    WHERE t.name = 'молочная продукция';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT p.name
    FROM product p
    WHERE p.name LIKE '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT p.name
    FROM product p
    WHERE p.expired_date BETWEEN '2019-11-01' AND '2019-11-30';

-- 4. Написать запрос, который выводит самый дорогой продукт.
--      1) определяем максимальную цену и ищём запись с такой ценой
SELECT p.name
    FROM product p
    WHERE p.price = (SELECT MAX(product.price)
                         FROM product);
--      2) сортируем по убыванию и берём первую запись
SELECT p.name
    FROM product p
    ORDER BY p.price DESC
    LIMIT 1;

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(p.id)
    FROM product p
    WHERE p.type_id = 1;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.name
    FROM product       p
             JOIN type t ON p.type_id = t.id
    WHERE t.name IN ('молочная продукция', 'консервы');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
-- (надо было вывести типы продуктов, разновидностей которых меньше 10 шт)
SELECT t.name
    FROM type             t
             JOIN product p ON p.type_id = t.id
    GROUP BY t.name
    HAVING count(*) < 10;

-- 8. Вывести все продукты и их тип.
SELECT p.name AS name, t.name AS type
    FROM product       p
             JOIN type t ON p.type_id = t.id;
