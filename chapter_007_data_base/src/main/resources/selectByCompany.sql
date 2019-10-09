DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS person;

CREATE TABLE company (
    id   INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

--  CONSTRAINT company_pkey
--  у ограничения задано имя. Это имя будет выводиться в сообщениях об ошибках.
--  Также по имени можно это ограничение удалить.
--  В случае если имя ограничения не задано явно, оно будет сгенерировано СУБД.

CREATE TABLE person (
    id         INTEGER NOT NULL,
    name       CHARACTER VARYING,
    company_id INTEGER,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT
    INTO company (id, name)
    VALUES (1, 'Яндекс'),
           (2, 'Гугл'),
           (3, 'Касперский'),
           (4, 'Епам'),
           (5, 'Люксофт');

INSERT
    INTO person (id, name, company_id)
    VALUES (1, 'Саша', 1),
           (2, 'Катя', 1),
           (3, 'Коля', 2),
           (4, 'Вася', 2),
           (5, 'Петя', 3),
           (6, 'Нина', 3),
           (7, 'Илья', 4),
           (8, 'Витя', 4),
           (9, 'Никита', 5),
           (10, 'Галя', 5);


-- 1) имена всех лиц, которые НЕ находятся в компании с id = 5
SELECT p.name
    FROM person p
    WHERE company_id != 5
;

-- 2) название компании для каждого человека
SELECT p.name, c.name AS company
    FROM person           p
             JOIN company c ON p.company_id = c.id
;

-- 3) Выберите название компании с максимальным количеством человек
--    плюс количество людей в этой компании

-- для начала просто выведем названия компаний и кол-во сотрудников
SELECT c.name, COUNT(c.id)
    FROM company         c
             JOIN person p ON p.company_id = c.id
    GROUP BY c.id
;

-- сложно было учесть случай когда максимумов НЕСКОЛЬКО

-- решение номер 1
SELECT *
    FROM (
             SELECT c1.name company_name, COUNT(*) q1
                 FROM company         c1
                          JOIN person p1 ON p1.company_id = c1.id
                 GROUP BY c1.id
         ) t1
    WHERE t1.q1 = (
        SELECT max(q2)
            FROM (
                     SELECT c2.name, COUNT(*) q2
                         FROM company         c2
                                  JOIN person p2 ON p2.company_id = c2.id
                         GROUP BY c2.id
                 ) t2
    )
;

-- решение номер 2
SELECT c.name, count(*) AS q
    FROM company         c
             JOIN person p ON p.company_id = c.id
    GROUP BY c.id
    HAVING count(*) = (SELECT max(q)
                           FROM (
                                    SELECT c.name, count(*) AS q
                                        FROM company         c
                                                 JOIN person p ON p.company_id = c.id
                                        GROUP BY c.id
                                ) t1
    )
;