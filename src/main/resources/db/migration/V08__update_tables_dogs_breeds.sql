-- -----------------------------------------------------
-- Drop fk_attendances_dogs_dog_id in tb_attendances
-- -----------------------------------------------------
ALTER TABLE tb_attendances
	DROP CONSTRAINT fk_attendances_dogs_dog_id;


-- -----------------------------------------------------
-- Drop and ReCreate table tb_dogs
-- -----------------------------------------------------
DROP TABLE tb_dogs;

CREATE TABLE IF NOT EXISTS tb_dogs (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	breed_id BIGINT NOT NULL,
	color VARCHAR(255) NULL,
	birth_date DATE NULL,
	registration_date DATE NOT NULL,
	client_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	INDEX fk_breed_idx (breed_id ASC) VISIBLE,
	CONSTRAINT fk_dogs_breeds_breed_id FOREIGN KEY (breed_id) REFERENCES tb_breeds (id),
	INDEX fk_client_idx (client_id ASC) VISIBLE,
	CONSTRAINT fk_dogs_clients_client_id FOREIGN KEY (client_id) REFERENCES tb_clients (id)
);

-- -----------------------------------------------------
-- Create tb_attendances fk_attendances_dogs_dog_id
-- -----------------------------------------------------
ALTER TABLE tb_attendances
	ADD CONSTRAINT fk_attendances_dogs_dog_id FOREIGN KEY (dog_id) REFERENCES tb_dogs (id);
	
-- -----------------------------------------------------
-- Update Table tb_breeds
-- -----------------------------------------------------
ALTER TABLE tb_breeds
	DROP COLUMN color,
 	ADD COLUMN origin VARCHAR(255) NULL;