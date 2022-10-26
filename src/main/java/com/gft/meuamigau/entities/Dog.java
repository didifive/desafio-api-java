package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_dogs")
public class Dog implements Serializable{
	private static final long serialVersionUID = 9060921240041137300L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "breed_id")
	private Breed breed;
	
	private String color;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "registration_date", nullable = false)
	private Date registrationDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dog")
	private List<Attendance> attendances = new ArrayList<>();
	
	public Dog(Long id) {
		this.id = id;
	}

}
