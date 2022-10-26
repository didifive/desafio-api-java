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
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_TEMPERAMENT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_VET_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_VET_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ATTENDANCE_VET_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.ValidationMessages.DEVE_CONTER_19_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.ID_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_16777215_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_65535_CARACTERES;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordAttendanceDTO {
	
	@Schema(type = SCHEMA_TYPE_INTEGER
			, title = SCHEMA_ATTENDANCE_DOG_ID_TITLE
			, description = SCHEMA_ATTENDANCE_DOG_ID_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_DOG_ID_EXAMPLE
			, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long dogId;
	
	@Schema(type = SCHEMA_TYPE_INTEGER
			, title = SCHEMA_ATTENDANCE_VET_ID_TITLE
			, description = SCHEMA_ATTENDANCE_VET_ID_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_VET_ID_EXAMPLE
			, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long vetId;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_DATE_TITLE
			, description = SCHEMA_ATTENDANCE_DATE_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_DATE_EXAMPLE
			, required = false
			, minLength = 19
			, maxLength = 19)
	@Size(min= 19, max = 19, message = DEVE_CONTER_19_CARACTERES)
	private String attendanceDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_DIAGNOSIS_TITLE
			, description = SCHEMA_ATTENDANCE_DIAGNOSIS_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_DIAGNOSIS_EXAMPLE
			, required = false)
	@Size(max = 65535, message = NAO_PODE_CONTER_MAIS_QUE_65535_CARACTERES)
	private String diagnosis;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_COMMENTS_TITLE
			, description = SCHEMA_ATTENDANCE_COMMENTS_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_COMMENTS_EXAMPLE
			, required = false)
	@Size(max = 16777215, message = NAO_PODE_CONTER_MAIS_QUE_16777215_CARACTERES)
	private String comments;
	
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE
        	, description = SCHEMA_ATTENDANCE_DOG_WEIGHT_DESCRIPTION
        	, example = SCHEMA_ATTENDANCE_DOG_WEIGHT_EXAMPLE
        	, required = false)
	@DecimalMin("0.01")
	@DecimalMax("9999.99")
	private double dogWeight;
	
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE
        	, description = SCHEMA_ATTENDANCE_DOG_HEIGHT_DESCRIPTION
        	, example = SCHEMA_ATTENDANCE_DOG_HEIGHT_EXAMPLE
        	, required = false)
	@DecimalMin("0.01")
	@DecimalMax("9999.99")
	private double dogHeight;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ATTENDANCE_TEMPERAMENT_TITLE
			, description = SCHEMA_ATTENDANCE_TEMPERAMENT_DESCRIPTION
			, example = SCHEMA_ATTENDANCE_TEMPERAMENT_EXAMPLE
			, required = false)
	@Size(max = 255, message = NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String temperament;
	
}
