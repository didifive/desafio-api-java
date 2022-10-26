package com.gft.meuamigau.dtos.breed;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BRED_FOR_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BRED_FOR_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BRED_FOR_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BREED_GROUP_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BREED_GROUP_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_BREED_GROUP_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_IMPERIAL_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_IMPERIAL_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_IMPERIAL_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_METRIC_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_METRIC_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_HEIGHT_METRIC_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_LIFE_SPAN_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_LIFE_SPAN_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_LIFE_SPAN_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ORIGIN_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ORIGIN_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_ORIGIN_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_REFERENCE_IMAGE_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_REFERENCE_IMAGE_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_REFERENCE_IMAGE_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_TEMPERAMENT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_TEMPERAMENT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_TEMPERAMENT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_IMPERIAL_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_IMPERIAL_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_IMPERIAL_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_METRIC_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_METRIC_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_WEIGHT_METRIC_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryBreedDTO {
	
	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_BREED_ID_TITLE
			, description = SCHEMA_BREED_ID_DESCRIPTION
			, example = SCHEMA_BREED_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_NAME_TITLE
			, description = SCHEMA_BREED_NAME_DESCRIPTION
			, example = SCHEMA_BREED_NAME_EXAMPLE
			, maxLength = 255)
	private String name;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_LIFE_SPAN_TITLE
			, description = SCHEMA_BREED_LIFE_SPAN_DESCRIPTION
			, example = SCHEMA_BREED_LIFE_SPAN_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String lifeSpan;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_BRED_FOR_TITLE
			, description = SCHEMA_BREED_BRED_FOR_DESCRIPTION
			, example = SCHEMA_BREED_BRED_FOR_EXAMPLE
			, maxLength = 65535
			, nullable = true)
	private String bredFor;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_BREED_GROUP_TITLE
			, description = SCHEMA_BREED_BREED_GROUP_DESCRIPTION
			, example = SCHEMA_BREED_BREED_GROUP_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String breedGroup;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_ORIGIN_TITLE
			, description = SCHEMA_BREED_ORIGIN_DESCRIPTION
			, example = SCHEMA_BREED_ORIGIN_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String origin;

	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_TEMPERAMENT_TITLE
			, description = SCHEMA_BREED_TEMPERAMENT_DESCRIPTION
			, example = SCHEMA_BREED_TEMPERAMENT_EXAMPLE
			, maxLength = 65535
			, nullable = true)
	private String temperament;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_REFERENCE_IMAGE_ID_TITLE
			, description = SCHEMA_BREED_REFERENCE_IMAGE_ID_DESCRIPTION
			, example = SCHEMA_BREED_REFERENCE_IMAGE_ID_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String referenceImageId;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_WEIGHT_IMPERIAL_TITLE
			, description = SCHEMA_BREED_WEIGHT_IMPERIAL_DESCRIPTION
			, example = SCHEMA_BREED_WEIGHT_IMPERIAL_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String weightImperial;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_WEIGHT_METRIC_TITLE
			, description = SCHEMA_BREED_WEIGHT_METRIC_DESCRIPTION
			, example = SCHEMA_BREED_WEIGHT_METRIC_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String weightMetric;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_HEIGHT_IMPERIAL_TITLE
			, description = SCHEMA_BREED_HEIGHT_IMPERIAL_DESCRIPTION
			, example = SCHEMA_BREED_HEIGHT_IMPERIAL_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String heightImperial;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_HEIGHT_METRIC_TITLE
			, description = SCHEMA_BREED_HEIGHT_METRIC_DESCRIPTION
			, example = SCHEMA_BREED_HEIGHT_METRIC_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String heightMetric;
	
}
