ALTER TABLE tb_clients
	DROP INDEX fk_user_idx,
	DROP CONSTRAINT fk_clients_users_user_id,
	DROP COLUMN user_id;
	
ALTER TABLE tb_vets
	DROP INDEX fk_user_idx,
	DROP CONSTRAINT fk_vets_users_user_id, 
	DROP COLUMN user_id;
	
ALTER TABLE tb_people
	DROP COLUMN email,
	ADD COLUMN email VARCHAR(255) NOT NULL,
	ADD COLUMN user_id BIGINT NOT NULL,
	ADD INDEX fk_user_idx (user_id ASC) VISIBLE,
	ADD CONSTRAINT fk_people_users_user_id FOREIGN KEY (user_id) REFERENCES tb_users (id);