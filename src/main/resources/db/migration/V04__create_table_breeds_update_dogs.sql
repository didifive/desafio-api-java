-- -----------------------------------------------------
-- Table tb_breeds
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_breeds (
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  weight VARCHAR(50) NOT NULL,
  height VARCHAR(50) NOT NULL,
  life_span VARCHAR(50) NOT NULL,
  bred_for TEXT NOT NULL,
  breed_group VARCHAR(100) NULL,
  PRIMARY KEY (id)
);


-- -----------------------------------------------------
-- Update Table tb_dogs
-- -----------------------------------------------------
ALTER TABLE tb_dogs
	DROP COLUMN breed,
	ADD COLUMN breed_id BIGINT NOT NULL,
	ADD INDEX fk_breed_idx (breed_id ASC) VISIBLE,
	ADD CONSTRAINT fk_dogs_breeds_breed_id FOREIGN KEY (breed_id) REFERENCES tb_breeds (id);