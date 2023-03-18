CREATE TABLE IF NOT EXISTS currency_name (
   currency_code VARCHAR(50) NOT NULL, 
   name VARCHAR(20) NOT NULL
);
INSERT INTO currency_name(currency_code, name) VALUES ('EUR', '歐元' );
INSERT INTO currency_name(currency_code, name) VALUES ('GBP', '英鎊' );
INSERT INTO currency_name(currency_code, name) VALUES ('USD', '美金' );