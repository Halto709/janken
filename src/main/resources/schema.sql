CREATE TABLE users (
    id IDENTITY,
    userName VARCHAR
);
CREATE TABLE matches (
    id IDENTITY,
    user1 INT,
    user2 INT,
    user1Hand VARCHAR,
    user2Hand VARCHAR
);
