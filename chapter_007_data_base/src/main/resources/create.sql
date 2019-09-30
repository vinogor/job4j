-- 1) Создать SQL скрипт инициализирующий создание новой базы данных.
DROP DATABASE job4j;
CREATE DATABASE job4j;

-- после подключаемся к созданной БД:
-- \connect job4j

-- 2) Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
CREATE TABLE roles (
    id   SERIAL PRIMARY KEY,
    role VARCHAR(20)
);

CREATE TABLE rules (
    id   SERIAL PRIMARY KEY,
    rule VARCHAR(100)
);

CREATE TABLE roles_and_rules (
    id       SERIAL PRIMARY KEY,
    roles_id INT REFERENCES roles (id),
    rules_id INT REFERENCES rules (id)
);

CREATE TABLE users (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(20),
    role_id INT REFERENCES roles (id)
);

CREATE TABLE categories (
    id       SERIAL PRIMARY KEY,
    category VARCHAR(20)
);

CREATE TABLE states (
    id    SERIAL PRIMARY KEY,
    state VARCHAR(20)
);


CREATE TABLE items (
    id          SERIAL PRIMARY KEY,
    user_id     INT REFERENCES users (id),
    category_id INT REFERENCES categories (id),
    state_id    INT REFERENCES states (id),
    item        VARCHAR(20)
);

CREATE TABLE attaches (
    id      SERIAL PRIMARY KEY,
    item_id INT REFERENCES items (id),
    files   TEXT
);

CREATE TABLE comments (
    id      SERIAL PRIMARY KEY,
    item_id INT REFERENCES items (id),
    comment TEXT
);


-- Создать SQL скрипт заполняющий начальные данные для системы заявок.
INSERT
    INTO roles (role)
    VALUES ('администратор');
INSERT
    INTO roles (role)
    VALUES ('пользователь');
INSERT
    INTO roles (role)
    VALUES ('менеджер');
INSERT
    INTO roles (role)
    VALUES ('директор');

INSERT
    INTO rules (rule)
    VALUES ('добавление нового пользователя');
INSERT
    INTO rules (rule)
    VALUES ('заведение новой заявки');
INSERT
    INTO rules (rule)
    VALUES ('изменение созданной заявки');
INSERT
    INTO rules (rule)
    VALUES ('изменение статуса заявки');

INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (1, 2);
INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (1, 3);
INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (1, 4);
INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (3, 2);
INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (3, 3);
INSERT
    INTO roles_and_rules (roles_id, rules_id)
    VALUES (4, 1);

INSERT
    INTO users (name, role_id)
    VALUES ('Саша', 4);
INSERT
    INTO users (name, role_id)
    VALUES ('Коля', 1);
INSERT
    INTO users (name, role_id)
    VALUES ('Миша', 2);
INSERT
    INTO users (name, role_id)
    VALUES ('Катя', 3);

INSERT
    INTO categories (category)
    VALUES ('опт');
INSERT
    INTO categories (category)
    VALUES ('розница');
INSERT
    INTO categories (category)
    VALUES ('сотрудничество');
INSERT
    INTO categories (category)
    VALUES ('бухгалтерия');

INSERT
    INTO states (state)
    VALUES ('новая');
INSERT
    INTO states (state)
    VALUES ('в работе');
INSERT
    INTO states (state)
    VALUES ('на согласовании');
INSERT
    INTO states (state)
    VALUES ('закрыта');
INSERT
    INTO states (state)
    VALUES ('архив');

INSERT
    INTO items (user_id, category_id, state_id, item)
    VALUES (1, 1, 1, 'запрос КП на опт');
INSERT
    INTO items (user_id, category_id, state_id, item)
    VALUES (4, 4, 3, 'повысить ЗП');

INSERT
    INTO attaches (item_id, files)
    VALUES (1, 'интересуемые_позиции.txt');
INSERT
    INTO attaches (item_id, files)
    VALUES (2, 'скан_указа.jpg');

INSERT
    INTO comments (item_id, comment)
    VALUES (1, 'дать скидку в 10%');
INSERT
    INTO comments (item_id, comment)
    VALUES (2, 'согласовать с исполнительным');

