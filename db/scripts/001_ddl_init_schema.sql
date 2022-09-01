CREATE TABLE IF NOT EXISTS auto_user
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);
CREATE TABLE IF NOT EXISTS auto_post
(
    id           SERIAL PRIMARY KEY,
    text         TEXT NOT NULL,
    created      TIMESTAMP ,
    auto_user_id INT REFERENCES auto_post (id)
);
CREATE TABLE IF NOT EXISTS engine
(
    id      SERIAL PRIMARY KEY,
    number VARCHAR(14),
    volume  NUMERIC(2) NOT NULL,
    horse_power INT NOT NULL,
    created TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS car
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    manufactured TIMESTAMP NOT NULL,
    engine_id    INT         NOT NULL UNIQUE REFERENCES engine (id)
);
CREATE TABLE IF NOT EXISTS driver
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(30) NOT NULL
);
CREATE TABLE IF NOT EXISTS history_owner
(
    id SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver(id),
    car_id INT NOT NULL REFERENCES car(id)
)
