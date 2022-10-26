package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_vets")
public class Vet implements Serializable {
	private static final long serialVersionUID = 847954890336501471L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "registration_date", nullable = false)
	private Date registrationDate;
	
	@Column(nullable = false)
	private String speciality;
	
	@Column(nullable = false)
	private String crmv;
	
	@Column(name = "crmv_uf", nullable = false)
	private String crmvUf;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "person_id", unique = true)
	private Person person;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "vet")
	private List<Attendance> attendances = new ArrayList<>();
	
	public Vet(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(crmv, crmvUf, registrationDate, speciality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vet other = (Vet) obj;
		return Objects.equals(crmv, other.crmv) && Objects.equals(crmvUf, other.crmvUf)
				&& Objects.equals(registrationDate, other.registrationDate)
				&& Objects.equals(speciality, other.speciality);
	}	
	
}
