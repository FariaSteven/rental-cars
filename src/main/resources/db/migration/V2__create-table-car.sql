CREATE TABLE CARS (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    model TEXT NOT NULL,
    color TEXT NOT NULL,
    engine TEXT NOT NULL,
    doors TEXT NOT NULL,
    gearbox TEXT NOT NULL,
    fuel TEXT NOT NULL,
    dimensions TEXT NOT NULL,
    trunk TEXT NOT NULL,
    avgConsumptionCity TEXT,
    avgConsumptionRoad TEXT,
    power TEXT NOT NULL,
    isRented BOOLEAN,
    rentedBy TEXT,
    price NUMERIC,
    airbag TEXT,
    brake TEXT
);