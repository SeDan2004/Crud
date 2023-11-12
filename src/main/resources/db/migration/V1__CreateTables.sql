
CREATE TABLE "companies" (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    director TEXT NOT NULL
);

CREATE TABLE "positions" (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE "employees" (
    id SERIAL PRIMARY KEY,
    fio TEXT,
    date_of_birthday DATE,
    position_id INTEGER NOT NULL REFERENCES positions(id),
    company_id INTEGER NOT NULL REFERENCES companies(id)
)