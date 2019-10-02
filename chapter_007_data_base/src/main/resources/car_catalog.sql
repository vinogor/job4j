DROP SCHEMA IF EXISTS car_catalog CASCADE;
CREATE SCHEMA car_catalog;

SET search_path TO car_catalog, public;

-- Создать структур данных в базе.
-- Таблицы.
--    Кузов. Двигатель, Коробка передач.
-- Создать структуру Машина. Машина не может существовать без данных из п.1.
-- Заполнить таблицы через insert.

-- кузов
CREATE TABLE bodywork (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

-- двигатель
CREATE TABLE engine (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

-- коробка передач
CREATE TABLE transmission (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

-- коробка передач
CREATE TABLE car (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(50),
    bodywork_id     INT REFERENCES bodywork (id),
    engine_id       INT REFERENCES engine (id),
    transmission_id INT REFERENCES transmission (id)
);

INSERT
    INTO bodywork (name)
    VALUES ('седан'),
           ('купэ'),
           ('кабриолет'),
           ('пикап'),
           ('лимузин');

INSERT
    INTO engine (name)
    VALUES ('бензиновый'),
           ('газовый'),
           ('дизельный'),
           ('электрический'),
           ('паровой'),
           ('ядерный');

INSERT
    INTO transmission (name)
    VALUES ('маханическая'),
           ('автоматическая'),
           ('вариатор'),
           ('робот');


INSERT
    INTO car (name, bodywork_id, engine_id, transmission_id)
    VALUES ('Лада', '1', '1', '1'),
           ('Маз', '4', '3', '1'),
           ('BMW', '2', '1', '2'),
           ('Audi', '3', '2', '2'),
           ('Tesla', '1', '4', '2'),
           ('Citroen', '1', '2', '3'),
           ('Ferrari', '2', '1', '2'),
           ('Fiat', '2', '2', '2'),
           ('Jaguar', '3', '4', '2'),
           ('Land Rover', '4', '3', '3'),
           ('Maserati', '1', '4', '1'),
           ('Opel', '2', '3', '3');

-- Создать SQL запросы:

-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.id, c.name AS car, b.name AS bodywork, e.name AS engine, t.name AS transmission
    FROM car                              c
             LEFT OUTER JOIN bodywork     b ON c.bodywork_id = b.id
             LEFT OUTER JOIN engine       e ON c.engine_id = e.id
             LEFT OUTER JOIN transmission t ON c.transmission_id = t.id;

-- 2. Вывести отдельно детали, которые не используются в машине - кузова, двигатели, коробки передач.

-- 1) наверное не самый оптимальный вариант
SELECT b.name AS unused_datails
    FROM bodywork                b
             LEFT OUTER JOIN car c ON c.bodywork_id = b.id
    WHERE c.name IS NULL
UNION
SELECT e.name
    FROM engine                  e
             LEFT OUTER JOIN car c ON c.engine_id = e.id
    WHERE c.name IS NULL
UNION
SELECT t.name
    FROM transmission            t
             LEFT OUTER JOIN car c ON c.transmission_id = t.id
    WHERE c.name IS NULL
;

-- 2) не нашёл как слить 3 столбца в один (один под ругим), пришлось объединить значения
SELECT concat(b.name, '', e.name, '', t.name) AS unused_details
    FROM car                        c
             FULL JOIN bodywork     b ON c.bodywork_id = b.id
             FULL JOIN engine       e ON c.engine_id = e.id
             FULL JOIN transmission t ON c.transmission_id = t.id
    WHERE c.name IS NULL
;
