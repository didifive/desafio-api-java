package com.gft.meuamigau.dtos.breed;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_HEIGHT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_HEIGHT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_HEIGHT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_URL_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_URL_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_URL_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_WIDTH_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_WIDTH_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_BREED_IMAGE_WIDTH_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageBreedDTO {

	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_IMAGE_ID_TITLE
			, description = SCHEMA_BREED_IMAGE_ID_DESCRIPTION
			, example = SCHEMA_BREED_IMAGE_ID_EXAMPLE)
	private String id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_BREED_IMAGE_URL_TITLE
			, description = SCHEMA_BREED_IMAGE_URL_DESCRIPTION
			, example = SCHEMA_BREED_IMAGE_URL_EXAMPLE)
	private String url;
	
	@Schema(type = SCHEMA_TYPE_INTEGER
			, title = SCHEMA_BREED_IMAGE_WIDTH_TITLE
			, description = SCHEMA_BREED_IMAGE_WIDTH_DESCRIPTION
			, example = SCHEMA_BREED_IMAGE_WIDTH_EXAMPLE)
	private Integer width;
	
	@Schema(type = SCHEMA_TYPE_INTEGER
			, title = SCHEMA_BREED_IMAGE_HEIGHT_TITLE
			, description = SCHEMA_BREED_IMAGE_HEIGHT_DESCRIPTION
			, example = SCHEMA_BREED_IMAGE_HEIGHT_EXAMPLE)
	private Integer height;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageBreedDTO other = (ImageBreedDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(url, other.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, url);
	}
	
}
