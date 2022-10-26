package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_breeds")
public class Breed implements Serializable{
	private static final long serialVersionUID = 5845185666647839079L;
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "life_span")
	private String lifeSpan;
	
	@Column(name = "bred_for", columnDefinition = "text")
	private String bredFor;
	
	@Column(name = "breed_group")
	private String breedGroup;
	
	private String origin;

	@Type(type = "text")
	private String temperament;
	
	@Column(name = "reference_image_id")
	private String referenceImageId;
	
	@Column(name = "weight_imperial")
	private String weightImperial;
	
	@Column(name = "weight_metric")
	private String weightMetric;
	
	@Column(name = "height_imperial")
	private String heightImperial;
	
	@Column(name = "height_metric")
	private String heightMetric;
		
	public Breed(Long id) {
		this.id = id;
	}
	
	public Breed(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Breed other = (Breed) obj;
		return Objects.equals(name, other.name);
	}
	
}
