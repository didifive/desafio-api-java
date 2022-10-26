ALTER TABLE tb_clients
	DROP COLUMN registration_date,
	ADD COLUMN registration_date DATE NOT NULL;
	
ALTER TABLE tb_vets
	DROP COLUMN registration_date,
	ADD COLUMN registration_date DATE NOT NULL;