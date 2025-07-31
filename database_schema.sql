
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('director', 'user', 'manager')),
                       department_id INTEGER,
                       status VARCHAR(20) NOT NULL DEFAULT 'active' CHECK (status IN ('active', 'inactive', 'pending')),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
