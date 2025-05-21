CREATE TABLE IF NOT EXISTS users (
  id_user BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS banks (
  id_bank BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  initial_balance DECIMAL(12,2),
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users (id_user)
);

CREATE TABLE IF NOT EXISTS transactions (
  id_transaction BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  transaction_type VARCHAR(255),
  date DATE,
  amount DECIMAL(12,2),
  description VARCHAR(255),
  category VARCHAR(255),
  origin_bank BIGINT,
  destination_bank BIGINT,
  user_id BIGINT,
  FOREIGN KEY (origin_bank) REFERENCES banks(id_bank),
  FOREIGN KEY (destination_bank) REFERENCES banks(id_bank),
  FOREIGN KEY (user_id) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS scheduledTransactions (
  id_scheduled_transaction BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  recurence_type VARCHAR(255),
  payment_date DATE,
  total_installments INT,
  amount DECIMAL(12,2),
  description VARCHAR(255),
  category VARCHAR(255),
  bank_id BIGINT,
  user_id BIGINT,
  FOREIGN KEY (bank_id) REFERENCES banks(id_bank),
  FOREIGN KEY (user_id) REFERENCES users(id_user)
);