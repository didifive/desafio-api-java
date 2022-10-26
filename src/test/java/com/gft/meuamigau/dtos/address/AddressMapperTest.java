package com.gft.meuamigau.dtos.address;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gft.meuamigau.entities.Address;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.enums.AddressType;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> AddressMapper")
class AddressMapperTest {
	
	public static final Long ID = 1L;
	public static final String PUBLIC_PLACE = "Rua Mil";
	public static final String NUMBER = "5874";
	public static final String COMPLEMENT = "CONDOMINIO - APTO";
	public static final String ZIP_CODE = "11111111";
	public static final AddressType ADDRESS_TYPE = AddressType.RESIDENCIAL;
	
	private Address address;
	private RecordAddressDTO recordAddressDTO;
	private QueryAddressDTO queryAddressDTO;
		
	@BeforeEach
	private void setup() {
		address = new Address(ID, PUBLIC_PLACE, NUMBER, COMPLEMENT, ZIP_CODE, ADDRESS_TYPE, new Person());
		recordAddressDTO = RecordAddressDTO.builder()
							.publicPlace(PUBLIC_PLACE)
							.number(NUMBER)
							.complement(COMPLEMENT)
							.zipCode(ZIP_CODE)
							.addressType(ADDRESS_TYPE)
							.build();
		queryAddressDTO = QueryAddressDTO.builder()
							.id(ID)
            				.publicPlace(PUBLIC_PLACE)
            				.number(NUMBER)
            				.complement(COMPLEMENT)
            				.zipCode(ZIP_CODE)
            				.addressType(ADDRESS_TYPE)
            				.build();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<AddressMapper> constructor = AddressMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordAddressDTO para Address")
	void shouldConvertRecordAddressDTOToAddress()throws Exception {
		//then
		Address result = AddressMapper.fromDTO(recordAddressDTO);
		assertAll("Assert who expected Address is returned"
				,() -> assertEquals(address.getPublicPlace(),	result.getPublicPlace())
				,() -> assertEquals(address.getPublicPlace(),	result.getPublicPlace())
				,() -> assertEquals(address.getNumber(), 		result.getNumber())
				,() -> assertEquals(address.getComplement(), 	result.getComplement())
				,() -> assertEquals(address.getZipCode(), 		result.getZipCode())
				,() -> assertEquals(address.getAddressType(), 	result.getAddressType())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Address para QueryAddressDTO")
	void shouldConvertAddressToQueryAddressDTO()throws Exception {
		//then
		QueryAddressDTO result = AddressMapper.fromEntity(address);
		assertAll("Assert who expected QueryPersonDTO is returned"
				,() -> assertEquals(queryAddressDTO.getId(), 			result.getId())
				,() -> assertEquals(queryAddressDTO.getPublicPlace(),	result.getPublicPlace())
				,() -> assertEquals(queryAddressDTO.getPublicPlace(),	result.getPublicPlace())
				,() -> assertEquals(queryAddressDTO.getNumber(), 		result.getNumber())
				,() -> assertEquals(queryAddressDTO.getComplement(), 	result.getComplement())
				,() -> assertEquals(queryAddressDTO.getZipCode(), 		result.getZipCode())
				,() -> assertEquals(queryAddressDTO.getAddressType(), 	result.getAddressType())
		);
	}

}
