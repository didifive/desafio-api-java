package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.gft.meuamigau.enums.PersonType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_people")
public class Person implements Serializable{
	private static final long serialVersionUID = 6021881671555030981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "person_type", nullable = false)
	private PersonType personType;
	
	@Column(name = "cpf_cnpj", nullable = false, unique = true)
	private String cpfCnpj;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	private String phone;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "person"
			, cascade = {CascadeType.ALL}
			, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;
	
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Client client;
	
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Vet vet;
	
	public Person(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfCnpj, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
