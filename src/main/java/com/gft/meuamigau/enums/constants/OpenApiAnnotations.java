package com.gft.meuamigau.enums.constants;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;
import static com.gft.meuamigau.utils.DateTimeConverter.DATE_TIME_PATTERN;

public final class OpenApiAnnotations {

	private static final String PARA_ATUALIZACAO_DE_CADASTRO = "Para atualização de cadastro, se não for informada, será mantida a data que já está cadastrada.";
	private static final String PARA_CADASTRO_NOVO = "Para cadastro novo, se não for informada data, o sistema irá gerar a data automaticamente considerando data atual. ";
	
	/**
	 * Constants for @Tag annotation by HTTP method in MeuAmigauApiApplication
	 */
	public static final String TAG_POST = "HTTP Method 1. POST";
	public static final String TAG_GET = "HTTP Method 2. GET";
	public static final String TAG_PUT = "HTTP Method 3. PUT";
	public static final String TAG_PATCH = "HTTP Method 4. PATCH";
	public static final String TAG_DELETE = "HTTP Method 5. DELETE";
	
	/**
	 * Constants for @Tag annotation by Roles in MeuAmigauApiApplication
	 */
	public static final String PUBLIC = "ENDPOINTS by Authority 1. Acesso público";
	public static final String ONLY_OWN_USER = "ENDPOINTS by Authority 2. Acesso somente para o próprio usuário";
	public static final String ROLE_CLIENT = "ENDPOINTS by Authority 3. Acesso para clientes - ROLE_CLIENT";
	public static final String ROLE_CLIENT_ONLY_OWN_USER = "ENDPOINTS by Authority 3. Acesso somente para o próprio cliente - ROLE_CLIENT";
	public static final String ROLE_PERSON = "ENDPOINTS by Authority 4. Acesso para pessoas cadastradas - ROLE_PERSON";
	public static final String ROLE_PERSON_ONLY_OWN_USER = "ENDPOINTS by Authority 4. Acesso somente para a própria pessoa - ROLE_PERSON";
	public static final String ROLE_VET = "ENDPOINTS by Authority 5. Acesso para veterinários - ROLE_VET";
	public static final String ROLE_VET_ONLY_OWN_USER = "ENDPOINTS by Authority 5. Acesso somente para o próprio veterinário - ROLE_VET";
	public static final String ROLE_USER = "ENDPOINTS by Authority 6. Acesso para usuários do sistema (funcionários) - ROLE_USER";
	public static final String ROLE_ADMIN = "ENDPOINTS by Authority 7. Acesso para administrador do sistema - ROLE_ADMIN";
	public static final String ROLE_ADMIN_BUT_NOT_OWN_USER = "ENDPOINTS by Authority 7. Acesso para administrador, porém não para próprio usuário";

	
	/**
	 * Constants for @SecurityScheme annotation in MeuAmigauApiApplication
	 */
	public static final String SECURITY_SCHEME_BEARER_AUTH = "bearerAuth";
	public static final String SECURITY_SCHEME_DESCRIPTION = "JWT Authorization header using the Bearer scheme";
	
	/**
	 * Constants for @Schema annotation types
	 */
	public static final String SCHEMA_TYPE_ARRAY = "array";
	public static final String SCHEMA_TYPE_INTEGER = "integer";
	public static final String SCHEMA_TYPE_NUMBER = "number";
	public static final String SCHEMA_TYPE_STRING = "string";
	
	/**
	 * Constants for @Schema annotation in ApiErrorDTO
	 */
	public static final String SCHEMA_API_ERROR_TIMESTAMP_TITLE = "Timestamp";
	public static final String SCHEMA_API_ERROR_TIMESTAMP_DESCRIPTION = "Instante da mensagem";
	public static final String SCHEMA_API_ERROR_TIMESTAMP_EXAMPLE = "2022-07-25T12:30:26.876372900Z";
	public static final String SCHEMA_API_ERROR_STATUS_TITLE = "Status";
	public static final String SCHEMA_API_ERROR_STATUS_DESCRIPTION = "Código de Status HTTP";
	public static final String SCHEMA_API_ERROR_STATUS_EXAMPLE = "404";
	public static final String SCHEMA_API_ERROR_ERROR_TITLE = "Erro";
	public static final String SCHEMA_API_ERROR_ERROR_DESCRIPTION = "Tipo do erro";
	public static final String SCHEMA_API_ERROR_ERROR_EXAMPLE = "Recurso não encontrado";
	public static final String SCHEMA_API_ERROR_MESSAGE_TITLE = "Mensagem";
	public static final String SCHEMA_API_ERROR_MESSAGE_DESCRIPTION = "Mensagem do erro";
	public static final String SCHEMA_API_ERROR_MESSAGE_EXAMPLE = "Pessoa com id 10 não foi encontrada";
	public static final String SCHEMA_API_ERROR_PATH_TITLE = "Path";
	public static final String SCHEMA_API_ERROR_PATH_DESCRIPTION = "Caminho da soliticação";
	public static final String SCHEMA_API_ERROR_PATH_EXAMPLE = "/api/v1/people/10";
	
	/**
	 * Constants for @Schema annotation in Auth DTOs
	 */
	public static final String SCHEMA_AUTH_USERNAME_TITLE = "Nome de usuário";
	public static final String SCHEMA_AUTH_USERNAME_DESCRIPTION = "Nome de usuário para autenticação";
	public static final String SCHEMA_AUTH_USERNAME_EXAMPLE = "username@gft.com";
	public static final String SCHEMA_AUTH_PASSWORD_TITLE = "Senha";
	public static final String SCHEMA_AUTH_PASSWORD_DESCRIPTION = "Senha para autenticação";
	public static final String SCHEMA_AUTH_PASSWORD_EXAMPLE = "abcd1234";
	public static final String SCHEMA_AUTH_TOKEN_TITLE = "JWT Token";
	public static final String SCHEMA_AUTH_TOKEN_DESCRIPTION = "JWT Token to authorize in endpoints with Bearer format";
	public static final String SCHEMA_AUTH_TOKEN_EXAMPLE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI"
			+ "6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";	
	
	/**
	 * Constants for @Schema annotation in Address DTOs
	 */
	public static final String SCHEMA_ADDRESS_ID_TITLE = "Id";
	public static final String SCHEMA_ADDRESS_ID_DESCRIPTION = "Código do endereço";
	public static final String SCHEMA_ADDRESS_ID_EXAMPLE = "1";
	public static final String SCHEMA_ADDRESS_PUBLIC_PLACE_TITLE = "Logradouro";
	public static final String SCHEMA_ADDRESS_PUBLIC_PLACE_DESCRIPTION = "Logradouro do endereço";
	public static final String SCHEMA_ADDRESS_PUBLIC_PLACE_EXAMPLE = "Avenida Independência";
	public static final String SCHEMA_ADDRESS_NUMBER_TITLE = "Número";
	public static final String SCHEMA_ADDRESS_NUMBER_DESCRIPTION = "Número do endereço";
	public static final String SCHEMA_ADDRESS_NUMBER_EXAMPLE = "4567";
	public static final String SCHEMA_ADDRESS_COMPLEMENT_TITLE = "Complemento";
	public static final String SCHEMA_ADDRESS_COMPLEMENT_DESCRIPTION = "Complemento do endereço";
	public static final String SCHEMA_ADDRESS_COMPLEMENT_EXAMPLE = "Condominio - Bloco - Apto";
	public static final String SCHEMA_ADDRESS_ZIP_CODE_TITLE = "CEP";
	public static final String SCHEMA_ADDRESS_ZIP_CODE_DESCRIPTION = "CEP do endereço";
	public static final String SCHEMA_ADDRESS_ZIP_CODE_EXAMPLE = "12456000";
	public static final String SCHEMA_ADDRESS_ADDRESS_TYPE_TITLE = "Tipo de Endereço";
	public static final String SCHEMA_ADDRESS_ADDRESS_TYPE_DESCRIPTION = "Tipo de Endereço, se RESIDENCIAL, COMERCIAL ou CORRESPONDENCIA";
	public static final String SCHEMA_ADDRESS_ADDRESS_TYPE_EXAMPLE = "RESIDENCIAL";
	
	/**
	 * Constants for @Schema annotation in Person DTOs
	 */
	public static final String SCHEMA_PERSON_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_PERSON_ID_DESCRIPTION = "Código da pessoa";
	public static final String SCHEMA_PERSON_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_PERSON_NAME_TITLE = "Nome";
	public static final String SCHEMA_PERSON_NAME_DESCRIPTION = "Nome da pessoa";
	public static final String SCHEMA_PERSON_NAME_EXAMPLE = "João da Silva";
	public static final String SCHEMA_PERSON_PERSON_TYPE_TITLE = "Tipo de Pessoa";
	public static final String SCHEMA_PERSON_PERSON_TYPE_DESCRIPTION = "Tipo de Pessoa, se FISICA ou JURIDICA";
	public static final String SCHEMA_PERSON_PERSON_TYPE_EXAMPLE = "FISICA";
	public static final String SCHEMA_PERSON_CPF_CNPJ_TITLE = "CPF ou CNPJ";
	public static final String SCHEMA_PERSON_CPF_CNPJ_DESCRIPTION = "Se pessoa do tipo FISICA deve ser informado CPF com 11 caracteres, "
			+ "se pessoa do tipo JURIDICA deve ser informado CNPJ com 14 caracteres, "
			+ "para ambos informar somente os números, sem pontos, traços ou barras";
	public static final String SCHEMA_PERSON_CPF_CNPJ_EXAMPLE = "12345678925";
	public static final String SCHEMA_PERSON_BIRTH_DATE_TITLE = "Data de Nascimento da pessoa";
	public static final String SCHEMA_PERSON_BIRTH_DATE_DESCRIPTION = "Data de nascimento da pessoa com padrão \'"+DATE_PATTERN+"\'";
	public static final String SCHEMA_PERSON_BIRTH_DATE_EXAMPLE = "01/01/1990";
	public static final String SCHEMA_PERSON_PHONE_TITLE = "Telefone";
	public static final String SCHEMA_PERSON_PHONE_DESCRIPTION = "Informar telefone para contato";
	public static final String SCHEMA_PERSON_PHONE_EXAMPLE = "(16) 99999-9999";
	public static final String SCHEMA_PERSON_E_MAIL_TITLE = "E-mail";
	public static final String SCHEMA_PERSON_E_MAIL_DESCRIPTION = "Informar email válido de uso da pessoa, será utilizado para gerarusuário";
	public static final String SCHEMA_PERSON_E_MAIL_EXAMPLE = "pessoa@dominio.com";
	public static final String SCHEMA_PERSON_USERNAME_TITLE = "Nome de Usuário";
	public static final String SCHEMA_PERSON_USERNAME_DESCRIPTION = "Nome de usuário que está vinculado à pessoa";
	public static final String SCHEMA_PERSON_USERNAME_EXAMPLE = SCHEMA_PERSON_E_MAIL_EXAMPLE;
	
	/**
	 * Constants for @Schema annotation in Client DTOs
	 */
	public static final String SCHEMA_CLIENT_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_CLIENT_ID_DESCRIPTION = "Código do cliente";
	public static final String SCHEMA_CLIENT_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_CLIENT_REGISTRATION_DATE_TITLE = "Data de Registro do cliente";
	public static final String SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION = "Data de Registro do cliente na clínica com padrão \'"+DATE_PATTERN+"\'"
			+ PARA_CADASTRO_NOVO
			+ PARA_ATUALIZACAO_DE_CADASTRO;
	public static final String SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE = "01/01/2022";
	public static final String SCHEMA_CLIENT_PERSON_ID_TITLE = "Id/Código de pessoa";
	public static final String SCHEMA_CLIENT_PERSON_ID_DESCRIPTION = "Código do cadastro (id) de pessoa a qual o cliente irá se referir";
	public static final String SCHEMA_CLIENT_PERSON_ID_EXAMPLE = "1";
	public static final String SCHEMA_CLIENT_NAME_TITLE = "Nome";
	public static final String SCHEMA_CLIENT_NAME_DESCRIPTION = "Nome do cliente";
	public static final String SCHEMA_CLIENT_NAME_EXAMPLE = "João dos Santos";
	public static final String SCHEMA_CLIENT_USERNAME_TITLE = "Nome de usuário";
	public static final String SCHEMA_CLIENT_USERNAME_DESCRIPTION = "Nome de usuário para acesso ao sistema, vinculado à pessoa/cliente";
	public static final String SCHEMA_CLIENT_USERNAME_EXAMPLE = "email@dominio.com";
	
	/**
	 * Constants for @Schema annotation in User DTOs
	 */
	public static final String SCHEMA_USER_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_USER_ID_DESCRIPTION = "Código do usuário";
	public static final String SCHEMA_USER_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_USER_USERNAME_TITLE = SCHEMA_AUTH_USERNAME_TITLE;
	public static final String SCHEMA_USER_USERNAME_DESCRIPTION = SCHEMA_AUTH_USERNAME_DESCRIPTION;
	public static final String SCHEMA_USER_USERNAME_EXAMPLE = SCHEMA_AUTH_USERNAME_EXAMPLE;
	public static final String SCHEMA_USER_ROLE_NAME_TITLE = "Lista de perfis";
	public static final String SCHEMA_USER_ROLE_NAME_DESCRIPTION = "Lista de perfis que o usuário possui permissão, deve enviar como um string de arrays";
	public static final String SCHEMA_USER_ROLE_NAME_EXAMPLE = "\"ROLE_ADMIN\",\"ROLE_USER\"";
	public static final String SCHEMA_USER_OLD_PASSWORD_TITLE = "Senha atual";
	public static final String SCHEMA_USER_OLD_PASSWORD_DESCRIPTION = "Informar a senha atual, mínimo de 6 e máximo de 25 caracteres";
	public static final String SCHEMA_USER_OLD_PASSWORD_EXAMPLE = "123456gft";
	public static final String SCHEMA_USER_NEW_PASSWORD_TITLE = "Nova senha";
	public static final String SCHEMA_USER_NEW_PASSWORD_DESCRIPTION = "Informar a nova senha, mínimo de 6 e máximo de 25 caracteres";
	public static final String SCHEMA_USER_NEW_PASSWORD_EXAMPLE = "Gft@1234";
	
	/**
	 * Constants for @Schema annotation in Vet DTOs
	 */
	public static final String SCHEMA_VET_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_VET_ID_DESCRIPTION = "Código do veterinário";
	public static final String SCHEMA_VET_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_VET_NAME_TITLE = "Nome";
	public static final String SCHEMA_VET_NAME_DESCRIPTION = "Nome do veterinário";
	public static final String SCHEMA_VET_NAME_EXAMPLE = "Maria José da Silva";
	public static final String SCHEMA_VET_REGISTRATION_DATE_TITLE = "Data de Registro do veterinário no sistema";
	public static final String SCHEMA_VET_REGISTRATION_DATE_DESCRIPTION = "Data de Registro do veterinário na clínica com padrão \'"+DATE_PATTERN+"\'"
			+ PARA_CADASTRO_NOVO
			+ PARA_ATUALIZACAO_DE_CADASTRO;
	public static final String SCHEMA_VET_REGISTRATION_DATE_EXAMPLE = SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE;
	public static final String SCHEMA_VET_SPECIALITY_TITLE = "Especialidades";
	public static final String SCHEMA_VET_SPECIALITY_DESCRIPTION = "Nomes das especialides do médico veterinário";
	public static final String SCHEMA_VET_SPECIALITY_EXAMPLE = "Clínico Geral";
	public static final String SCHEMA_VET_CRMV_TITLE = "CRMV";
	public static final String SCHEMA_VET_CRMV_DESCRIPTION = "Número do registro no CRMV";
	public static final String SCHEMA_VET_CRMV_EXAMPLE = "45671891/342";
	public static final String SCHEMA_VET_CRMV_UF_TITLE = "CRMV UF";
	public static final String SCHEMA_VET_CRMV_UF_DESCRIPTION = "UF do registro no CRMV";
	public static final String SCHEMA_VET_CRMV_UF_EXAMPLE = "SP";
	public static final String SCHEMA_VET_PERSON_ID_TITLE = "Id/Código de pessoa";
	public static final String SCHEMA_VET_PERSON_ID_DESCRIPTION = "Código do cadastro (id) de pessoa a qual o veterinário irá se referir";
	public static final String SCHEMA_VET_PERSON_ID_EXAMPLE = "1";		
	public static final String SCHEMA_VET_USERNAME_TITLE = SCHEMA_USER_USERNAME_TITLE;
	public static final String SCHEMA_VET_USERNAME_DESCRIPTION = "Nome de usuário para acesso ao sistema, vinculado à pessoa/veterinário";
	public static final String SCHEMA_VET_USERNAME_EXAMPLE = SCHEMA_USER_USERNAME_EXAMPLE;
	
	
	/**
	 * Constants for @Schema annotation in Dog DTOs
	 */
	public static final String SCHEMA_DOG_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_DOG_ID_DESCRIPTION = "Código do cachorro";
	public static final String SCHEMA_DOG_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_DOG_NAME_TITLE = SCHEMA_PERSON_NAME_TITLE;
	public static final String SCHEMA_DOG_NAME_DESCRIPTION = "Nome do cachorro";
	public static final String SCHEMA_DOG_NAME_EXAMPLE = "Kitana";
	public static final String SCHEMA_DOG_BREED_ID_TITLE = "Código da raça";
	public static final String SCHEMA_DOG_BREED_ID_DESCRIPTION = "Código da raça ao qual este cachorro pertence, para Raça Não Definida "
			+ "(RND), Undefined Race, popularmente conhecido como \'vira-lata\', informar o id 999999999";
	public static final String SCHEMA_DOG_BREED_ID_EXAMPLE =  SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_DOG_BREED_NAME_TITLE = "Nome da raça";
	public static final String SCHEMA_DOG_BREED_NAME_DESCRIPTION = "Nome da raça do cachorro";
	public static final String SCHEMA_DOG_BREED_NAME_EXAMPLE =  "Afghan Hound";
	public static final String SCHEMA_DOG_COLOR_TITLE = "Cor(es) predominante(s)";
	public static final String SCHEMA_DOG_COLOR_DESCRIPTION = "Descrever cor(es) predominante(s) do cachorro";
	public static final String SCHEMA_DOG_COLOR_EXAMPLE = "Caramelo";
	public static final String SCHEMA_DOG_BIRTH_DATE_TITLE = "Data de Nascimento do cachorro";
	public static final String SCHEMA_DOG_BIRTH_DATE_DESCRIPTION = "Data de nascimento do cachorro com padrão \'"+DATE_PATTERN+"\'";
	public static final String SCHEMA_DOG_BIRTH_DATE_EXAMPLE = "01/01/2018";
	public static final String SCHEMA_DOG_REGISTRATION_DATE_TITLE = "Data de Registro do cachorro no sistema";
	public static final String SCHEMA_DOG_REGISTRATION_DATE_DESCRIPTION = "Data de Registro do cachorro na clínica com padrão \'"+DATE_PATTERN+"\'"
			+ PARA_CADASTRO_NOVO
			+ PARA_ATUALIZACAO_DE_CADASTRO;
	public static final String SCHEMA_DOG_REGISTRATION_DATE_EXAMPLE = "01/01/2022";
	public static final String SCHEMA_DOG_CLIENT_ID_TITLE = "Código do cliente";
	public static final String SCHEMA_DOG_CLIENT_ID_DESCRIPTION = "Código do cliente responsável pelo cachorro";
	public static final String SCHEMA_DOG_CLIENT_ID_EXAMPLE =  SCHEMA_ADDRESS_ID_EXAMPLE;
	
	/**
	 * Constants for @Schema annotation in Breed DTOs
	 */
	public static final String SCHEMA_BREED_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_BREED_ID_DESCRIPTION = "Código da raça";
	public static final String SCHEMA_BREED_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_BREED_NAME_TITLE = SCHEMA_PERSON_NAME_TITLE;
	public static final String SCHEMA_BREED_NAME_DESCRIPTION = "Nome da raça";
	public static final String SCHEMA_BREED_NAME_EXAMPLE = SCHEMA_DOG_BREED_NAME_EXAMPLE;
	public static final String SCHEMA_BREED_LIFE_SPAN_TITLE = "Longevidade";
	public static final String SCHEMA_BREED_LIFE_SPAN_DESCRIPTION = "Longevidade da raça";
	public static final String SCHEMA_BREED_LIFE_SPAN_EXAMPLE = "7 - 10";
	public static final String SCHEMA_BREED_BRED_FOR_TITLE = "Criação";
	public static final String SCHEMA_BREED_BRED_FOR_DESCRIPTION = "Características de criação da raça";
	public static final String SCHEMA_BREED_BRED_FOR_EXAMPLE = "Coursing and hunting";
	public static final String SCHEMA_BREED_BREED_GROUP_TITLE = "Grupo";
	public static final String SCHEMA_BREED_BREED_GROUP_DESCRIPTION = "Grupo da raça";
	public static final String SCHEMA_BREED_BREED_GROUP_EXAMPLE = "Hound";
	public static final String SCHEMA_BREED_ORIGIN_TITLE = "Origem";
	public static final String SCHEMA_BREED_ORIGIN_DESCRIPTION = "País(es) de origem da raça";
	public static final String SCHEMA_BREED_ORIGIN_EXAMPLE = "England, Poland";
	public static final String SCHEMA_BREED_TEMPERAMENT_TITLE = "Temperamento";
	public static final String SCHEMA_BREED_TEMPERAMENT_DESCRIPTION = "Temperamentos gerais da raça";
	public static final String SCHEMA_BREED_TEMPERAMENT_EXAMPLE = "Aloof, Clownish, Dignified, Independent, Happy";
	public static final String SCHEMA_BREED_REFERENCE_IMAGE_ID_TITLE = "Código da Imagem";
	public static final String SCHEMA_BREED_REFERENCE_IMAGE_ID_DESCRIPTION = "Id/Código de imagem de exemplar da raça";
	public static final String SCHEMA_BREED_REFERENCE_IMAGE_ID_EXAMPLE = "hMyT4CDXR";
	public static final String SCHEMA_BREED_WEIGHT_IMPERIAL_TITLE = "Peso padrão imperial";
	public static final String SCHEMA_BREED_WEIGHT_IMPERIAL_DESCRIPTION = "Intervalo de peso da raça considerando a medida imperial - unidade em libras";
	public static final String SCHEMA_BREED_WEIGHT_IMPERIAL_EXAMPLE = "44 - 66";
	public static final String SCHEMA_BREED_WEIGHT_METRIC_TITLE = "Peso padrão métrico";
	public static final String SCHEMA_BREED_WEIGHT_METRIC_DESCRIPTION = "Intervalo de peso da raça considerando padrão métrico - unidade em Kilogramas";
	public static final String SCHEMA_BREED_WEIGHT_METRIC_EXAMPLE = "20 - 30";
	public static final String SCHEMA_BREED_HEIGHT_IMPERIAL_TITLE = "Altura padrão imperial";
	public static final String SCHEMA_BREED_HEIGHT_IMPERIAL_DESCRIPTION = "Intervalo de altura da raça considerando a medida imperial - unidade em pés";
	public static final String SCHEMA_BREED_HEIGHT_IMPERIAL_EXAMPLE = "30";
	public static final String SCHEMA_BREED_HEIGHT_METRIC_TITLE = "Altura padrão métrico";
	public static final String SCHEMA_BREED_HEIGHT_METRIC_DESCRIPTION = "Intervalo de peso da raça considerando padrão métrico - unidade em centímetros";
	public static final String SCHEMA_BREED_HEIGHT_METRIC_EXAMPLE = "76";
	public static final String SCHEMA_BREED_IMAGE_ID_TITLE = "Id";
	public static final String SCHEMA_BREED_IMAGE_ID_DESCRIPTION = "Id/Código da imagem";
	public static final String SCHEMA_BREED_IMAGE_ID_EXAMPLE = "H6h17XOYn";
	public static final String SCHEMA_BREED_IMAGE_URL_TITLE = "URL";
	public static final String SCHEMA_BREED_IMAGE_URL_DESCRIPTION = "URL do arquivo de imagem";
	public static final String SCHEMA_BREED_IMAGE_URL_EXAMPLE = "https://cdn2.thedogapi.com/images/H6h17XOYn.jpg";
	public static final String SCHEMA_BREED_IMAGE_WIDTH_TITLE = "Largura";
	public static final String SCHEMA_BREED_IMAGE_WIDTH_DESCRIPTION = "Largura da imagem";
	public static final String SCHEMA_BREED_IMAGE_WIDTH_EXAMPLE = "1200";
	public static final String SCHEMA_BREED_IMAGE_HEIGHT_TITLE = "Altura";
	public static final String SCHEMA_BREED_IMAGE_HEIGHT_DESCRIPTION = "Altura da imagem";
	public static final String SCHEMA_BREED_IMAGE_HEIGHT_EXAMPLE = "800";
	
	/**
	 * Constants for @Schema annotation in Attendance DTOs
	 */
	public static final String SCHEMA_ATTENDANCE_ID_TITLE = SCHEMA_ADDRESS_ID_TITLE;
	public static final String SCHEMA_ATTENDANCE_ID_DESCRIPTION = "Código do atendimento";
	public static final String SCHEMA_ATTENDANCE_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_ATTENDANCE_DOG_ID_TITLE = "Id/Código do cachorro";
	public static final String SCHEMA_ATTENDANCE_DOG_ID_DESCRIPTION = "Id/Código do cachorro em atendimento";
	public static final String SCHEMA_ATTENDANCE_DOG_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_ATTENDANCE_VET_ID_TITLE = "Id/Código do veterinário";
	public static final String SCHEMA_ATTENDANCE_VET_ID_DESCRIPTION = "Id/Código do veterinário que está atendendo";
	public static final String SCHEMA_ATTENDANCE_VET_ID_EXAMPLE = SCHEMA_ADDRESS_ID_EXAMPLE;
	public static final String SCHEMA_ATTENDANCE_DATE_TITLE = "Data e hora do atendimento";
	public static final String SCHEMA_ATTENDANCE_DATE_DESCRIPTION = "Data e hora do atendimento no padrão \'"+DATE_TIME_PATTERN+"\'";
	public static final String SCHEMA_ATTENDANCE_DATE_EXAMPLE = "01/01/2022 08:30:30";
	public static final String SCHEMA_ATTENDANCE_DIAGNOSIS_TITLE = "Diagnóstico";
	public static final String SCHEMA_ATTENDANCE_DIAGNOSIS_DESCRIPTION = "Diagnóstico do animal";
	public static final String SCHEMA_ATTENDANCE_DIAGNOSIS_EXAMPLE = "Diagnóstico do animal, situação geral";
	public static final String SCHEMA_ATTENDANCE_COMMENTS_TITLE = "Comentários";
	public static final String SCHEMA_ATTENDANCE_COMMENTS_DESCRIPTION = "Comentários gerais referentes ao atendimento";
	public static final String SCHEMA_ATTENDANCE_COMMENTS_EXAMPLE = "Comentários gerais referentes ao atendimento, discorrer aqui";
	public static final String SCHEMA_ATTENDANCE_DOG_WEIGHT_TITLE = "Peso do cachorro";
	public static final String SCHEMA_ATTENDANCE_DOG_WEIGHT_DESCRIPTION = "Registro de peso do cachorro, unidade em Kg, sistema métrico";
	public static final String SCHEMA_ATTENDANCE_DOG_WEIGHT_EXAMPLE = "20.5";
	public static final String SCHEMA_ATTENDANCE_DOG_HEIGHT_TITLE = "Tamanho do cachorro";
	public static final String SCHEMA_ATTENDANCE_DOG_HEIGHT_DESCRIPTION = "Registro do tamanho do cachorro, unidade em cm, sistema métrico";
	public static final String SCHEMA_ATTENDANCE_DOG_HEIGHT_EXAMPLE = "120.8";
	public static final String SCHEMA_ATTENDANCE_TEMPERAMENT_TITLE = "Temperamento";
	public static final String SCHEMA_ATTENDANCE_TEMPERAMENT_DESCRIPTION = "Temperamento do cachorro";
	public static final String SCHEMA_ATTENDANCE_TEMPERAMENT_EXAMPLE = "Descrever o temperamento do animal no atendimento";
	
	private OpenApiAnnotations() {
		throw new IllegalAccessError("Utility Class");
	}

}
