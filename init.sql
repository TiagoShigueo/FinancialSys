CREATE TABLE IF NOT EXISTS users (
  id_user BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS banks (
  id_bank BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users (id_user)
);

CREATE TABLE IF NOT EXISTS transactions (
  id_transaction BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  transactionType VARCHAR(255),
  amount double,
  description VARCHAR(255),
  category VARCHAR(255),
  origin_bank BIGINT,
  destination_bank BIGINT,
  user_id BIGINT,
  FOREIGN KEY (origin_bank) REFERENCES banks(id_bank),
  FOREIGN KEY (destination_bank) REFERENCES banks(id_bank),
  FOREIGN KEY (user_id) REFERENCES users(id_user)
);
