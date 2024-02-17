CREATE TABLE fx_deals (
    deal_id SERIAL PRIMARY KEY,
    from_currency_iso_code CHAR(3) NOT NULL,
    to_currency_iso_code CHAR(3) NOT NULL,
    deal_timestamp TIMESTAMP NOT NULL,
    deal_amount NUMERIC(15, 2) NOT NULL
);
