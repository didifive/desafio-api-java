package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.gft.meuamigau.dtos.address.QueryAddressDTO;
import com.gft.meuamigau.dtos.address.RecordAddressDTO;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.person.AbstractPersonDTO;
import com.gft.meuamigau.dtos.person.DetailsPersonDTO;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Address;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.enums.PersonType;

public class PersonUtils {
	
	public static final Long ID = 1L;
	public static final String NAME = "João da Silva";
	public static final PersonType PERSON_TYPE = PersonType.JURIDICA;
	public static final String CPF_CNPJ = "45351749000111";
	public static final String DATE_STRING = "13/12/1990";
	public static final Date DATE = DateTimeConverter.stringtoDate(DATE_STRING, DATE_PATTERN);
	public static final String PHONE = "(11) 99999-9999";
	public static final String EMAIL = "joaodasilva@gft.com";
	public static final List<Address> ADDRESSES = List.of(new Address());
	public static final List<RecordAddressDTO> RECORD_ADDRESSES = List.of(new RecordAddressDTO());
	public static final List<QueryAddressDTO> QUERY_ADDRESSES = List.of(new QueryAddressDTO());
	public static final User USER = new User(ID, EMAIL, CPF_CNPJ, new HashSet<>(), null, new ArrayList<>());
	public static final Client CLIENT = new Client(ID, DATE, null, new ArrayList<>(), new ArrayList<>());
	public static final Vet VET = new Vet(ID,DATE, "","","",null,new ArrayList<>());
	
	
	public static Person createFakeEntity() {
		return new Person(
					ID
					, NAME
					, PERSON_TYPE
					, CPF_CNPJ
					, DATE
					, PHONE
					, EMAIL
					, ADDRESSES
					, USER
					, CLIENT
					, VET
				);
	}
	
	public static RecordPersonDTO createFakeRecordPersonDTO() {
		return RecordPersonDTO.builder()
				.name(NAME)
				.personType(PERSON_TYPE)
				.cpfCnpj(CPF_CNPJ)
				.email(EMAIL)
				.birthDate(DATE_STRING)
				.phone(PHONE)
				.addresses(RECORD_ADDRESSES)
				.build();
	}
	
	public static QueryPersonDTO createFakeQueryPersonDTO() {
		return QueryPersonDTO.builder()
				.id(ID)
				.name(NAME)
				.personType(PERSON_TYPE)
				.cpfCnpj(CPF_CNPJ)
				.email(EMAIL)
				.birthDate(DATE_STRING)
				.phone(PHONE)
				.addresses(QUERY_ADDRESSES)
				.username(USER.getUsername())
				.build();
	}
	
	public static DetailsPersonDTO createFakeDetailsPersonDTO() {
		return DetailsPersonDTO.builder()
				.id(ID)
				.name(NAME)
				.personType(PERSON_TYPE)
				.cpfCnpj(CPF_CNPJ)
				.email(EMAIL)
				.birthDate(DATE_STRING)
				.phone(PHONE)
				.addresses(QUERY_ADDRESSES)
				.client(ClientMapper.toAbstractDTO(CLIENT))
				.vet(VetMapper.toAbstractDTO(VET))
				.username(USER.getUsername())
				.build();
	}
	
	public static AbstractPersonDTO createFakeAbstractPersonDTO() {
		return AbstractPersonDTO.builder()
				.id(ID)
				.name(NAME)
				.personType(PERSON_TYPE)
				.email(EMAIL)
				.build();
	}
	
}
