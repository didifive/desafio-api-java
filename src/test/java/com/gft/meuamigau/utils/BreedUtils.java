package com.gft.meuamigau.utils;

import com.gft.meuamigau.dtos.breed.BreedHeightDTO;
import com.gft.meuamigau.dtos.breed.BreedWeightDTO;
import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.dtos.breed.RequestBreedDTO;
import com.gft.meuamigau.entities.Breed;

public class BreedUtils {
	
	public static final Long ID = 1L;
	public static final String NAME = "Affenpinscher";
	public static final String LIFE_SPAN = "10 - 12 years";
	public static final String BRED_FOR = "Small rodent hunting, lapdog";
	public static final String BREED_GROUP = "Toy";
	public static final String ORIGIN = "Germany, France";
	public static final String TEMPERAMENT = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving";
	public static final String REFERENCE_IMAGE_ID = "BJa4kxc4X";
	public static final String WEIGHT_IMPERIAL = "6 - 13";
	public static final String WEIGHT_METRIC = "3 - 6";
	public static final String HEIGHT_IMPERIAL = "9 - 11.5";
	public static final String HEIGHT_METRIC = "23 - 29";
	
	public static final String IMAGE_URL = "https://cdn2.thedogapi.com/images/BJa4kxc4X_1280.jpg";
	public static final Integer WIDTH = 1600;
	public static final Integer HEIGHT = 1199;
	
	public static Breed createFakeEntity() {
		Breed breed = new Breed();
		breed.setId(ID);
		breed.setName(NAME);
		breed.setLifeSpan(LIFE_SPAN);
		breed.setBredFor(BRED_FOR);
		breed.setBreedGroup(BREED_GROUP);
		breed.setOrigin(ORIGIN);
		breed.setTemperament(TEMPERAMENT);
		breed.setReferenceImageId(REFERENCE_IMAGE_ID);
		breed.setWeightImperial(WEIGHT_IMPERIAL);
		breed.setWeightMetric(WEIGHT_METRIC);
		breed.setHeightImperial(HEIGHT_IMPERIAL);
		breed.setHeightMetric(HEIGHT_METRIC);
		return breed;
	}
	
	public static QueryBreedDTO createFakeQueryBreedDTO() {
		return QueryBreedDTO.builder()
				.id(ID)
				.name(NAME)
				.lifeSpan(LIFE_SPAN)
				.bredFor(BRED_FOR)
				.breedGroup(BREED_GROUP)
				.origin(ORIGIN)
				.temperament(TEMPERAMENT)
				.referenceImageId(REFERENCE_IMAGE_ID)
				.weightImperial(WEIGHT_IMPERIAL)
				.weightMetric(WEIGHT_METRIC)
				.heightImperial(HEIGHT_IMPERIAL)
				.heightMetric(HEIGHT_METRIC)
				.build();
	}
	
	public static RequestBreedDTO createFakeRequestBreedDTO() {
		BreedWeightDTO weight = new BreedWeightDTO(WEIGHT_IMPERIAL, WEIGHT_METRIC);
		BreedHeightDTO height = new BreedHeightDTO(HEIGHT_IMPERIAL, HEIGHT_METRIC);
		
		return RequestBreedDTO.builder()
				.id(ID)
				.name(NAME)
				.lifeSpan(LIFE_SPAN)
				.bredFor(BRED_FOR)
				.breedGroup(BREED_GROUP)
				.origin(ORIGIN)
				.temperament(TEMPERAMENT)
				.referenceImageId(REFERENCE_IMAGE_ID)
				.origin(ORIGIN)
				.weight(weight)
				.height(height)
				.build();
	}
	
	public static ImageBreedDTO createFakeImageBreedDTO() {
		return ImageBreedDTO.builder()
				.id(REFERENCE_IMAGE_ID)
				.url(IMAGE_URL)
				.width(WIDTH)
				.height(HEIGHT)
				.build();
	}
	
	public static String createFakeListBreedReturnsInJSON() {
		return "["
				+ "  {"
				+ "    \"weight\": {"
				+ "      \"imperial\": \"6 - 13\","
				+ "      \"metric\": \"3 - 6\""
				+ "    },"
				+ "    \"height\": {"
				+ "      \"imperial\": \"9 - 11.5\","
				+ "      \"metric\": \"23 - 29\""
				+ "    },"
				+ "    \"id\": 1,"
				+ "    \"name\": \"Affenpinscher\","
				+ "    \"bred_for\": \"Small rodent hunting, lapdog\","
				+ "    \"breed_group\": \"Toy\","
				+ "    \"life_span\": \"10 - 12 years\","
				+ "    \"temperament\": \"Stubborn, Curious, Playful, Adventurous, Active, Fun-loving\","
				+ "    \"origin\": \"Germany, France\","
				+ "    \"reference_image_id\": \"BJa4kxc4X\","
				+ "    \"image\": {"
				+ "      \"id\": \"BJa4kxc4X\","
				+ "      \"width\": 1600,"
				+ "      \"height\": 1199,"
				+ "      \"url\": \"https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg\""
				+ "    }"
				+ "  },"
				+ "  {"
				+ "    \"weight\": {"
				+ "      \"imperial\": \"50 - 60\","
				+ "      \"metric\": \"23 - 27\""
				+ "    },"
				+ "    \"height\": {"
				+ "      \"imperial\": \"25 - 27\","
				+ "      \"metric\": \"64 - 69\""
				+ "    },"
				+ "    \"id\": 2,"
				+ "    \"name\": \"Afghan Hound\","
				+ "    \"country_code\": \"AG\","
				+ "    \"bred_for\": \"Coursing and hunting\","
				+ "    \"breed_group\": \"Hound\","
				+ "    \"life_span\": \"10 - 13 years\","
				+ "    \"temperament\": \"Aloof, Clownish, Dignified, Independent, Happy\","
				+ "    \"origin\": \"Afghanistan, Iran, Pakistan\","
				+ "    \"reference_image_id\": \"hMyT4CDXR\","
				+ "    \"image\": {"
				+ "      \"id\": \"hMyT4CDXR\","
				+ "      \"width\": 606,"
				+ "      \"height\": 380,"
				+ "      \"url\": \"https://cdn2.thedogapi.com/images/hMyT4CDXR.jpg\""
				+ "    }"
				+ "  },"
				+ "  {"
				+ "    \"weight\": {"
				+ "      \"imperial\": \"44 - 66\","
				+ "      \"metric\": \"20 - 30\""
				+ "    },"
				+ "    \"height\": {"
				+ "      \"imperial\": \"30\","
				+ "      \"metric\": \"76\""
				+ "    },"
				+ "    \"id\": 3,"
				+ "    \"name\": \"African Hunting Dog\","
				+ "    \"bred_for\": \"A wild pack animal\","
				+ "    \"life_span\": \"11 years\","
				+ "    \"temperament\": \"Wild, Hardworking, Dutiful\","
				+ "    \"origin\": \"\","
				+ "    \"reference_image_id\": \"rkiByec47\","
				+ "    \"image\": {"
				+ "      \"id\": \"rkiByec47\","
				+ "      \"width\": 500,"
				+ "      \"height\": 335,"
				+ "      \"url\": \"https://cdn2.thedogapi.com/images/rkiByec47.jpg\""
				+ "    }"
				+ "  }"
				+ "]";
	}
	
	public static String createFakeListOfPinBreedReturnsInJSON() {
		return "["
				+ "  {"
				+ "    \"weight\": {"
				+ "      \"imperial\": \"6 - 13\","
				+ "      \"metric\": \"3 - 6\""
				+ "    },"
				+ "    \"height\": {"
				+ "      \"imperial\": \"9 - 11.5\","
				+ "      \"metric\": \"23 - 29\""
				+ "    },"
				+ "    \"id\": 1,"
				+ "    \"name\": \"Affenpinscher\","
				+ "    \"bred_for\": \"Small rodent hunting, lapdog\","
				+ "    \"breed_group\": \"Toy\","
				+ "    \"life_span\": \"10 - 12 years\","
				+ "    \"temperament\": \"Stubborn, Curious, Playful, Adventurous, Active, Fun-loving\","
				+ "    \"origin\": \"Germany, France\","
				+ "    \"reference_image_id\": \"BJa4kxc4X\","
				+ "    \"image\": {"
				+ "      \"id\": \"BJa4kxc4X\","
				+ "      \"width\": 1600,"
				+ "      \"height\": 1199,"
				+ "      \"url\": \"https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg\""
				+ "    }"
				+ "  }"
				+ "]";
	}
	
	public static String createFakeImageBreedReturnInJSON() {
    	return "{"
            	+ "  \"id\": \"BJa4kxc4X\","
            	+ "  \"url\": \"https://cdn2.thedogapi.com/images/BJa4kxc4X_1280.jpg\","
            	+ "  \"breeds\": ["
            	+ "    {"
            	+ "      \"weight\": {"
            	+ "        \"imperial\": \"6 - 13\","
            	+ "        \"metric\": \"3 - 6\""
            	+ "      },"
            	+ "      \"height\": {"
            	+ "        \"imperial\": \"9 - 11.5\","
            	+ "        \"metric\": \"23 - 29\""
            	+ "      },"
            	+ "      \"id\": 1,"
            	+ "      \"name\": \"Affenpinscher\","
            	+ "      \"bred_for\": \"Small rodent hunting, lapdog\","
            	+ "      \"breed_group\": \"Toy\","
            	+ "      \"life_span\": \"10 - 12 years\","
            	+ "      \"temperament\": \"Stubborn, Curious, Playful, Adventurous, Active, Fun-loving\","
            	+ "      \"origin\": \"Germany, France\","
            	+ "      \"reference_image_id\": \"BJa4kxc4X\""
            	+ "    }"
            	+ "  ],"
            	+ "  \"width\": 1600,"
            	+ "  \"height\": 1199"
            	+ "}";
	}
}
