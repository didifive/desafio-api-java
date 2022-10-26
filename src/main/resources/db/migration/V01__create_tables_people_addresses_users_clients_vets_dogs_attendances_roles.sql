-- -----------------------------------------------------
-- Table tb_people
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_people (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  person_type VARCHAR(8) NOT NULL,
  cpf_cnpj VARCHAR(14) NOT NULL,
  birth_date DATE NOT NULL,
  phone VARCHAR(45) NOT NULL,
  email VARCHAR(255) NULL,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table tb_addresses
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_addresses (
  id BIGINT NOT NULL AUTO_INCREMENT,
  public_place VARCHAR(255) NOT NULL,
  number VARCHAR(15) NOT NULL,
  complement VARCHAR(255) NULL,
  zip_code VARCHAR(8) NOT NULL,
  address_type VARCHAR(20) NOT NULL,
  person_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_person_idx (person_id ASC) VISIBLE,
  CONSTRAINT fk_addresses_people_person_id FOREIGN KEY (person_id) REFERENCES tb_people (id)
);

-- -----------------------------------------------------
-- Table tb_users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NULL,
  password VARCHAR(70) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE
);

-- -----------------------------------------------------
-- Table tb_clients
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_clients (
  id BIGINT NOT NULL AUTO_INCREMENT,
  registration_date TIMESTAMP NULL,
  person_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_person_idx (person_id ASC) VISIBLE,
  INDEX fk_user_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_clients_people_person_id FOREIGN KEY (person_id) REFERENCES tb_people (id),
  CONSTRAINT fk_clients_users_user_id FOREIGN KEY (user_id) REFERENCES tb_users (id)
);

-- -----------------------------------------------------
-- Table tb_vets
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_vets (
  id BIGINT NOT NULL AUTO_INCREMENT,
  registration_date TIMESTAMP NOT NULL,
  specialty VARCHAR(255) NOT NULL,
  crmv VARCHAR(45) NOT NULL,
  crmv_uf VARCHAR(2) NOT NULL,
  person_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_person_idx (person_id ASC) VISIBLE,
  INDEX fk_user_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_vets_people_person_id FOREIGN KEY (person_id) REFERENCES tb_people (id),
  CONSTRAINT fk_vets_users_user_id FOREIGN KEY (user_id) REFERENCES tb_users (id)
);

-- -----------------------------------------------------
-- Table tb_dogs
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_dogs (
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  breed VARCHAR(255) NULL,
  birth_date DATE NULL,
  registration_date DATE NOT NULL,
  client_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_client_idx (client_id ASC) VISIBLE,
  CONSTRAINT fk_dogs_clients_client_id FOREIGN KEY (client_id) REFERENCES tb_clients (id)
);

-- -----------------------------------------------------
-- tb_attendances
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_attendances (
  id BIGINT NOT NULL AUTO_INCREMENT,
  dog_id BIGINT NOT NULL,
  client_id BIGINT NOT NULL,
  vet_id BIGINT NOT NULL,
  attendance_date DATETIME NOT NULL,
  diagnosis TEXT NULL,
  comments LONGTEXT NULL,
  dog_weight DECIMAL(6,2) NULL,
  dog_height DECIMAL(6,2) NULL,
  dog_temperament VARCHAR(255) NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_dog_idx (dog_id ASC) VISIBLE,
  INDEX fk_vet_idx (vet_id ASC) VISIBLE,
  INDEX fk_client_idx (client_id ASC) VISIBLE,
  INDEX fk_users_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_attendances_dogs_dog_id FOREIGN KEY (dog_id) REFERENCES tb_dogs (id),
  CONSTRAINT fk_attendances_vets_vet_id FOREIGN KEY (vet_id) REFERENCES tb_vets (id),
  CONSTRAINT fk_attendances_clients_client_id FOREIGN KEY (client_id) REFERENCES tb_clients (id),
  CONSTRAINT fk_attendances_users_user_id FOREIGN KEY (user_id) REFERENCES tb_users (id)
);

-- -----------------------------------------------------
-- Table tb_roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_roles (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE
);

-- -----------------------------------------------------
-- Table tb_user_role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  INDEX fk_user_idx (user_id ASC) VISIBLE,
  INDEX fk_role_idx (role_id ASC) VISIBLE,
  CONSTRAINT fk_user_role_users_user_id FOREIGN KEY (user_id) REFERENCES tb_users (id),
  CONSTRAINT fk_user_role_roles_role_id FOREIGN KEY (role_id) REFERENCES tb_roles (id)
);