package com.gft.meuamigau.dtos.address;

import com.gft.meuamigau.entities.Address;

public class AddressMapper {
	
	private AddressMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	/**
	 * Convert RecordAddressDTO to Address
	 * @param RecordAddressDTO dto
	 * @return Address
	 */
	public static Address fromDTO(RecordAddressDTO dto) {
		return new Address(
				null
				, dto.getPublicPlace()
				, dto.getNumber()
				, dto.getComplement()
				, dto.getZipCode()
				, dto.getAddressType()
				, null
		);
		
	}	
	
	/**
	 * Convert Address to QueryAddressDTO
	 * @param Address address
	 * @return QueryAddressDTO
	 */
	public static QueryAddressDTO fromEntity(Address address) {
		return QueryAddressDTO.builder()
				.id(address.getId())
				.publicPlace(address.getPublicPlace())
				.number(address.getNumber())
				.complement(address.getComplement())
				.zipCode(address.getZipCode())
				.addressType(address.getAddressType())
				.build();
	}
	
}
