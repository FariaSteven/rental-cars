CREATE TABLE PRODUCTS (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    model TEXT NOT NULL,
    color TEXT NOT NULL,
    engine TEXT NOT NULL,
    doors INTEGER NOT NULL,
    gearbox BOOLEAN NOT NULL,
    fuel TEXT NOT NULL,
    dimensions TEXT NOT NULL,
    trunk INTEGER NOT NULL,
    avgConsumptionCity NUMERIC NOT NULL,
    avgConsumptionRoad NUMERIC NOT NULL,
    power INTEGER NOT NULL,
    isRented BOOLEAN NOT NULL,
    price NUMERIC NOT NULL
);