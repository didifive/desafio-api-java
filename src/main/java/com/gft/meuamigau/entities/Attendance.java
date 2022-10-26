package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_attendances")
public class Attendance implements Serializable {
	private static final long serialVersionUID = -465139676573412934L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "attendance_date", nullable = false)
	private Date attendanceDate;
	
	@Type(type="text")
	private String diagnosis;
	
	@Lob
	private String comments;
	
	@Column(name = "dog_weight", precision = 2, columnDefinition = "decimal")
	private double dogWeight;
	
	@Column(name = "dog_height", precision = 2, columnDefinition = "decimal")
	private double dogHeight;
	
	@Column(name = "dog_temperament")
	private String dogTemperament;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dog_id")
	private Dog dog;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vet_id")
	private Vet vet;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Attendance(Long id) {
		this.id = id;
	}

}
