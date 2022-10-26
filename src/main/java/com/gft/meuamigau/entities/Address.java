package com.gft.meuamigau.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.meuamigau.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_addresses")
public class Address implements Serializable{
	private static final long serialVersionUID = -4541507578159140444L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "public_place", nullable = false)
	private String publicPlace;
	
	@Column(nullable = false)
	private String number;
	
	private String complement;
	
	@Column(name = "zip_code", nullable = false)
	private String zipCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "address_type", nullable = false)
	private AddressType addressType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@Override
	public int hashCode() {
		return Objects.hash(addressType, complement, number, publicPlace, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressType == other.addressType && Objects.equals(complement, other.complement)
				&& Objects.equals(number, other.number) && Objects.equals(publicPlace, other.publicPlace)
				&& Objects.equals(zipCode, other.zipCode);
	}

}
