-- -----------------------------------------------------
-- Update Table tb_vets
-- -----------------------------------------------------
ALTER TABLE tb_vets
	DROP COLUMN specialty,
	ADD COLUMN speciality VARCHAR(255) NOT NULL;