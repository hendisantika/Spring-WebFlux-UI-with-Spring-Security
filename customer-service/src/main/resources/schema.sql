CREATE TABLE customer
(
    id            numeric(10)             NOT NULL PRIMARY KEY,
    name          varchar_ignorecase(100) NOT NULL,
    membership_id numeric(10)             NOT NULL
);
