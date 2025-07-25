CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       phone VARCHAR(20),
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);
