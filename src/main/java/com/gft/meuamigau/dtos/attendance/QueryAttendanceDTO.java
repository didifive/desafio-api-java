package com.gft.meuamigau.dtos.attendance;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_COMMENTS_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_COMMENTS_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_COMMENTS_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DIAGNOSIS_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DIAGNOSIS_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DIAGNOSIS_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_HEIGHT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_HEIGHT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gft.meuamigau.dtos.client.AbstractClientDTO;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.vet.AbstractVetDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAttendanceDTO {
	
	@Schema(type = SCHEMA_TYPE_INTEGER
			, title = SCHEMA_ATTENDANCE_ID_TITLE
			, description = SCHEMA_ATTENDANCE_ID_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_ID_EXAMPLE)
	private Long id;
	
	private QueryDogDTO dog;
	
	private AbstractClientDTO client;
	
	private AbstractVetDTO vet;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_DATE_TITLE
			, description = SCHEMA_ATTENDANCE_DATE_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_DATE_EXAMPLE)
	private String attendanceDate;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_DIAGNOSIS_TITLE
			, description = SCHEMA_ATTENDANCE_DIAGNOSIS_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_DIAGNOSIS_EXAMPLE
			, nullable = true
			, maxLength = 65535)
	private String diagnosis;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_COMMENTS_TITLE
			, description = SCHEMA_ATTENDANCE_COMMENTS_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_COMMENTS_EXAMPLE
			, nullable = true
			, maxLength = 16777215)
	private String comments;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE
        	, description = SCHEMA_ATTENDANCE_DOG_WEIGHT_DESCRIPTION
        	, example = SCHEMA_ATTENDANCE_DOG_WEIGHT_EXAMPLE
        	, nullable = true)
	private double dogWeight;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE
        	, description = SCHEMA_ATTENDANCE_DOG_HEIGHT_DESCRIPTION
        	, example = SCHEMA_ATTENDANCE_DOG_HEIGHT_EXAMPLE
        	, nullable = true)
	private double dogHeight;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_TEMPERAMENT_TITLE
			, description = SCHEMA_ATTENDANCE_TEMPERAMENT_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_TEMPERAMENT_EXAMPLE
			, nullable = true
			, maxLength = 255)
	private String temperament;
	
}
