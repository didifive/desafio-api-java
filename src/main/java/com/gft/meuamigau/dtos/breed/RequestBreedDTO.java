package com.gft.meuamigau.dtos.breed;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBreedDTO {

	private Long id;
	
	private String name;
	
	@SerializedName("life_span")
	private String lifeSpan;
	
	@SerializedName("bred_for")
	private String bredFor;
	
	@SerializedName("breed_group")
	private String breedGroup;

	private String temperament;
	
	private String origin;
	
	@SerializedName("reference_image_id")
	private String referenceImageId;
	
	private BreedWeightDTO weight;
	
	private BreedHeightDTO height;
}
