CREATE TABLE trips
(
    id INTEGER,
    sensor VARCHAR(255),
    time TIMESTAMP,
    type VARCHAR(255),
    value INTEGER,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);

CREATE TABLE sensors
(
    id VARCHAR(255),
    type VARCHAR(255)
);

CREATE TABLE MetaType
(
    name VARCHAR(255),
    unit VARCHAR(255)
);