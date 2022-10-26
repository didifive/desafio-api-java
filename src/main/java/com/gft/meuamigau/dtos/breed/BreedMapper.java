package com.gft.meuamigau.dtos.breed;

import com.gft.meuamigau.entities.Breed;

public class BreedMapper {
	
	private BreedMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	/**
	 * Convert RequestBreedDTO to Breed
	 * @param RequestBreedDTO dto
	 * @return Breed
	 */
	public static Breed toEntity(RequestBreedDTO dto) {
		Breed breed = new Breed();
		breed.setId(dto.getId());
		breed.setName(dto.getName());
		breed.setLifeSpan(dto.getLifeSpan());
		breed.setBredFor(dto.getBredFor());
		breed.setBreedGroup(dto.getBreedGroup());
		breed.setOrigin(dto.getOrigin());
		breed.setTemperament(dto.getTemperament());
		breed.setReferenceImageId(dto.getReferenceImageId());
		
		if(dto.getWeight() != null) {
    		breed.setWeightImperial(dto.getWeight().getImperial());
    		breed.setWeightMetric(dto.getWeight().getMetric());
		}
		if(dto.getHeight() != null) {
    		breed.setHeightImperial(dto.getHeight().getImperial());
    		breed.setHeightMetric(dto.getHeight().getMetric());
		}
		
		return breed;
	}
	
	/**
	 * Convert Breed to QueryBreedDTO
	 * @param Breed breed
	 * @return QueryBreedDTO
	 */
	public static QueryBreedDTO toQueryDTO(Breed breed) {
		
		return QueryBreedDTO.builder()
				.id(breed.getId())
				.name(breed.getName())
				.lifeSpan(breed.getLifeSpan())
				.bredFor(breed.getBredFor())
				.breedGroup(breed.getBreedGroup())
				.temperament(breed.getTemperament())
				.origin(breed.getOrigin())
				.referenceImageId(breed.getReferenceImageId())
				.weightImperial(breed.getWeightImperial())
				.weightMetric(breed.getWeightMetric())
				.heightImperial(breed.getHeightImperial())
				.heightMetric(breed.getHeightMetric())
				.build();
	}
	
	/**
	 * Convert RequestBreedDTO to QueryBreedDTO
	 * @param RequestBreedDTO dto
	 * @return QueryBreedDTO
	 */
	public static QueryBreedDTO toQueryDTO(RequestBreedDTO dto) {
		
		 QueryBreedDTO queryBreedDTO = QueryBreedDTO.builder()
				.id(dto.getId())
				.name(dto.getName())
				.lifeSpan(dto.getLifeSpan())
				.bredFor(dto.getBredFor())
				.breedGroup(dto.getBreedGroup())
				.origin(dto.getOrigin())
				.temperament(dto.getTemperament())
				.referenceImageId(dto.getReferenceImageId())
				.build();
		
		if(dto.getWeight() != null) {
			queryBreedDTO.setWeightImperial(dto.getWeight().getImperial());
			queryBreedDTO.setWeightMetric(dto.getWeight().getMetric());
		}
		if(dto.getWeight() != null) {
			queryBreedDTO.setHeightImperial(dto.getHeight().getImperial());
			queryBreedDTO.setHeightMetric(dto.getHeight().getMetric());
		}
		
		return queryBreedDTO;
	}
	
}
