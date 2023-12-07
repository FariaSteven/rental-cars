CREATE TABLE CARS (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    model TEXT NOT NULL,
    color TEXT NOT NULL,
    engine TEXT NOT NULL,
    doors INTEGER NOT NULL,
    gearbox TEXT NOT NULL,
    fuel TEXT NOT NULL,
    dimensions TEXT NOT NULL,
    trunk INTEGER NOT NULL,
    avgConsumptionCity TEXT NOT NULL,
    avgConsumptionRoad TEXT NOT NULL,
    power INTEGER NOT NULL,
    isRented BOOLEAN NOT NULL,
    rentedBy TEXT NOT NULL,
    price NUMERIC NOT NULL,
    airbag INTEGER NOT NULL,
    brake TEXT NOT NULL
);