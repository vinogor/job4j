--  Нельзя изменять уже примененные change-set-ы !!!
--  Нужно только создавать новые.

CREATE TABLE IF NOT EXISTS items (
    id       SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR(30),
    describe TEXT,
    time     TIMESTAMP
);