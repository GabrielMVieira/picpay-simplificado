 create table transactions (

    id BIGSERIAL primary key,
    amount NUMERIC(38,2),
    payer_id BIGINT,
    payee_id BIGINT,
    transaction_time TIMESTAMP,
    FOREIGN KEY (payer_id) REFERENCES users(id),
    FOREIGN KEY (payee_id) REFERENCES users(id)

);