CREATE TABLE goals (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    target_amount DECIMAL(19, 2) NOT NULL,
    current_amount DECIMAL(19, 2) DEFAULT 0,
    deadline DATE
);