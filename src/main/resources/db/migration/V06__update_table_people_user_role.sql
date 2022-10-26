-- -----------------------------------------------------
-- Update Table tb_people
-- -----------------------------------------------------
ALTER TABLE tb_people
	ADD UNIQUE INDEX cpf_cnpj_UNIQUE (cpf_cnpj ASC) VISIBLE,
	DROP COLUMN email,
	ADD COLUMN email VARCHAR(255) NOT NULL,
	ADD UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE,
	DROP COLUMN birth_date,
	DROP COLUMN phone,
	ADD COLUMN birth_date DATE NULL,
	ADD COLUMN phone VARCHAR(45) NULL;
	
	
-- -----------------------------------------------------
-- Update Table tb_users
-- -----------------------------------------------------
ALTER TABLE tb_users
	DROP INDEX username_UNIQUE,
	DROP COLUMN username,
	ADD COLUMN username VARCHAR(100) NOT NULL,
	ADD UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE,
	DROP COLUMN password,
	ADD COLUMN password VARCHAR(70) NOT NULL;
	
-- -----------------------------------------------------
-- Update Table tb_roles
-- -----------------------------------------------------
ALTER TABLE tb_roles
	DROP INDEX name_UNIQUE,
	DROP COLUMN name,
	ADD COLUMN name VARCHAR(100) NOT NULL,
	ADD UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE;