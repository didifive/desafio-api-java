package com.gft.meuamigau.config.populate;

import static com.gft.meuamigau.enums.AddressType.COMERCIAL;
import static com.gft.meuamigau.enums.AddressType.CORRESPONDENCIA;
import static com.gft.meuamigau.enums.AddressType.RESIDENCIAL;
import static com.gft.meuamigau.enums.PersonType.FISICA;
import static com.gft.meuamigau.enums.PersonType.JURIDICA;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.meuamigau.dtos.address.RecordAddressDTO;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.services.PersonService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PersonPopulate {	
	
	private final PersonService personService;
	
	private static final RecordAddressDTO ADDRESS_1 = new RecordAddressDTO(
			"Rua Abraao Moreira"
			, "1234"
			, null
			, "12369474"
			, RESIDENCIAL);
	private static final List<RecordAddressDTO> RECORD_ADDRESSES_1 = List.of(ADDRESS_1);
	RecordPersonDTO givenPersonDTO = RecordPersonDTO.builder()
			.name("João da Silva")
			.personType(FISICA)
			.cpfCnpj("59342976000")
			.birthDate("13/12/1990")
			.phone("(11) 99999-9999")
			.email("joaodasilva@gft.com")
			.addresses(RECORD_ADDRESSES_1)
			.build();
	
	private static final RecordAddressDTO ADDRESS_2 = new RecordAddressDTO(
			"Avenida Independência"
			, "360"
			, "Condominio Escritórios"
			, "12396002"
			, COMERCIAL);
	private static final List<RecordAddressDTO> RECORD_ADDRESSES_2 = List.of(ADDRESS_2);
	RecordPersonDTO givenPersonDTO2 = RecordPersonDTO.builder()
			.name("ONG Protetora dos ViraLatas Caramelos")
			.personType(JURIDICA)
			.cpfCnpj("43182654000196")
			.birthDate("13/12/2002")
			.phone("(11) 99999-5555")
			.email("protetoraviralatacaramelo@protetoraviralatacaramelo.com")
			.addresses(RECORD_ADDRESSES_2)
			.build();
	
	private static final RecordAddressDTO ADDRESS_3 = new RecordAddressDTO(
			"Avenida dos Ypês"
			, "150"
			, "Residencial Ypê"
			, "11369741"
			, RESIDENCIAL);
	private static final RecordAddressDTO ADDRESS_4 = new RecordAddressDTO(
			"Praça Central"
			, "1"
			, "Caixa Postal 500"
			, "11222357"
			, CORRESPONDENCIA);
	private static final List<RecordAddressDTO> RECORD_ADDRESSES_3 = List.of(ADDRESS_3,ADDRESS_4);
	RecordPersonDTO givenPersonDTO3 = RecordPersonDTO.builder()
			.name("Maria dos Santos")
			.personType(FISICA)
			.cpfCnpj("33923615000")
			.birthDate("13/12/1980")
			.phone("(11) 98888-9999")
			.email("mariasantos@gft.com")
			.addresses(RECORD_ADDRESSES_3)
			.build();
	
	private static final RecordAddressDTO ADDRESS_5 = new RecordAddressDTO(
			"Rua Abraao Moreira"
			, "1234"
			, null
			, "12651474"
			, RESIDENCIAL);
	private static final List<RecordAddressDTO> RECORD_ADDRESSES_4 = List.of(ADDRESS_5);
	RecordPersonDTO givenPersonDTO4 = RecordPersonDTO.builder()
			.name("Ken")
			.personType(FISICA)
			.cpfCnpj("24345756847")
			.birthDate("13/12/1980")
			.phone("(11) 99779-9799")
			.email("keneh@gft.com")
			.addresses(RECORD_ADDRESSES_4)
			.build();

	public void createPersons() {
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<QueryPersonDTO> queryPeople = personService.findAll(pageable);
		
		if(queryPeople.getContent().isEmpty()) {
			List<RecordPersonDTO> listaPessoas = List.of(givenPersonDTO, givenPersonDTO2, givenPersonDTO3, givenPersonDTO4);
			listaPessoas.forEach(personService::create);
		}
		
    }
}