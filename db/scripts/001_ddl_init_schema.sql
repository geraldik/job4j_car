CREATE TABLE IF NOT EXISTS driver
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    login    VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS engine
(
    id          SERIAL PRIMARY KEY,
    volume      NUMERIC(2) NOT NULL,
    horse_power INT        NOT NULL,
    is_diesel   BOOLEAN    NOT NULL
);
CREATE TABLE IF NOT EXISTS gearbox
(
    id           SERIAL PRIMARY KEY,
    is_automatic BOOLEAN NOT NULL,
    speed_number INT     NOT NULL
);
CREATE TABLE IF NOT EXISTS car_body
(
    id           SERIAL PRIMARY KEY,
    color        VARCHAR(64) NOT NULL,
    body_type    VARCHAR(20) NOT NULL,
    doors_number INT         NOT NULL
);
CREATE TABLE IF NOT EXISTS brand
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS car
(
    id               SERIAL PRIMARY KEY,
    model            VARCHAR(50) NOT NULL,
    manufacture_year INT         NOT NULL,
    engine_id        INT         NOT NULL UNIQUE REFERENCES engine (id),
    gearbox_id       INT         NOT NULL REFERENCES gearbox (id),
    car_body_id      INT         NOT NULL REFERENCES car_body (id),
    brand_id         INT         NOT NULL REFERENCES brand (id)
);
CREATE TABLE IF NOT EXISTS auto_post
(
    id        SERIAL PRIMARY KEY,
    text      TEXT NOT NULL,
    created   TIMESTAMP,
    photo     BYTEA,
    driver_id INT  NOT NULL REFERENCES driver (id),
    car_id    INT  NOT NULL REFERENCES car (id),
    sold BOOLEAN
);
CREATE TABLE IF NOT EXISTS history_owner
(
    id        SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver (id),
    car_id    INT NOT NULL REFERENCES car (id)
);