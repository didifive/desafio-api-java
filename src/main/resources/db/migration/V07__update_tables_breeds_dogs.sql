-- -----------------------------------------------------
-- Update Table tb_breeds
-- -----------------------------------------------------
ALTER TABLE tb_breeds
	DROP COLUMN weight,
  	DROP COLUMN height,
  	DROP COLUMN life_span,
  	DROP COLUMN bred_for,
 	DROP COLUMN breed_group,
 	ADD COLUMN color VARCHAR(255) NULL,
  	ADD COLUMN life_span VARCHAR(255) NULL,
  	ADD COLUMN bred_for TEXT NULL,
  	ADD COLUMN breed_group VARCHAR(100) NULL,
  	ADD COLUMN reference_image_id VARCHAR(255) NULL,
  	ADD COLUMN temperament TEXT NULL,
	ADD COLUMN height_imperial VARCHAR(255) NULL,
	ADD COLUMN height_metric VARCHAR(255) NULL,
	ADD COLUMN weight_imperial VARCHAR(255) NULL,
	ADD COLUMN weight_metric VARCHAR(255) NULL;
	
-- -----------------------------------------------------
-- Update Table tb_dogs
-- -----------------------------------------------------
ALTER TABLE tb_dogs
	ADD COLUMN color VARCHAR(255) NULL;